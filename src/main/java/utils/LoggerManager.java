package utils;
import java.io.IOException;
import java.util.logging.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class LoggerManager {
    private static final Logger logger = Logger.getLogger(LoggerManager.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.SEVERE);
            logger.addHandler(consoleHandler);
            SMTPHandler emailHandler = new SMTPHandler("smtp.gmail.com", "yudchenkok@gmail.com", "flrnqspafhixdagc", "yudchenkok@gmail.com");
            emailHandler.setLevel(Level.SEVERE);
            logger.addHandler(emailHandler);
        } catch (IOException e) {
            System.err.println("Помилка логування: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static class SMTPHandler extends Handler {
        private String host;
        private String from;
        private String to;
        private String password;

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

        @Override
        public void flush() {
        }

        @Override
        public void close() throws SecurityException {
        }

        private void sendEmail(LogRecord record) throws MessagingException {
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
                    "Дата: " + record.getMillis());
            Transport.send(message);
            System.out.println("Електронну пошту успішно надіслано.");
        }
    }
}
