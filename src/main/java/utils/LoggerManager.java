package utils;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.util.logging.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class LoggerManager {
    public static void configureLogging() {
        try {
            Dotenv dotenv = Dotenv.load();
            String emailFrom = dotenv.get("GMAIL_FROM");
            String emailTo = dotenv.get("GMAIL_TO");
            String emailPassword = dotenv.get("GMAIL_APP_PASSWORD");

            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.SEVERE);

            SMTPHandler emailHandler = new SMTPHandler("smtp.gmail.com", emailFrom, emailPassword, emailTo);
            emailHandler.setLevel(Level.SEVERE);

            Logger rootLogger = Logger.getLogger("");
            rootLogger.setLevel(Level.INFO);
            rootLogger.addHandler(fileHandler);
            rootLogger.addHandler(consoleHandler);
            rootLogger.addHandler(emailHandler);
        } catch (IOException e) {
            System.err.println("Помилка логування: " + e.getMessage());
        }
    }

    public static class SMTPHandler extends Handler {
        private final String host;
        private final String from;
        private final String to;
        private final String password;

        public SMTPHandler(String host, String from, String password, String to) {
            this.host = host;
            this.from = from;
            this.password = password;
            this.to = to;
        }

        @Override
        public void publish(LogRecord record) {
            if (record.getLevel() == Level.SEVERE) {
                try {
                    sendEmail(record);
                } catch (MessagingException e) {
                    System.err.println("Не вдалося надіслати електронну пошту: " + e.getMessage());
                }
            }
        }

        @Override public void flush() {}
        @Override public void close() throws SecurityException {}

        protected void sendEmail(LogRecord record) throws MessagingException {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Критична помилка в програмі");
            message.setText("Повідомлення про помилку: " + record.getMessage() + "\n" +
                    "Дата: " + new java.util.Date(record.getMillis()));
            Transport.send(message);
        }
    }
}

