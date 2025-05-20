package utils;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import java.util.List;
import java.util.logging.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoggerManagerTest {
    LoggerManager.SMTPHandler smtpHandler;

    @BeforeEach
    void setUp() {
        smtpHandler = Mockito.spy(new LoggerManager.SMTPHandler(
                "smtp.test.com",
                "from@test.com",
                "password",
                "to@test.com"
        ));
    }

    @Test
    void testPublish_DoesNotSendEmail_ForInfoLevel() throws Exception {
        LogRecord infoRecord = new LogRecord(Level.INFO, "Info-level message");
        smtpHandler.publish(infoRecord);
        verify(smtpHandler, never()).sendEmail(any());
    }

    @Test
    void testPublish_DoesNotSendEmail_ForWarningLevel() throws Exception {
        LogRecord warningRecord = new LogRecord(Level.WARNING, "Warning-level message");
        smtpHandler.publish(warningRecord);
        verify(smtpHandler, never()).sendEmail(any());
    }

    @Test
    void testPublish_SendsEmail_ForSevereLevel() throws Exception {
        LogRecord severeRecord = new LogRecord(Level.SEVERE, "Severe-level message");
        doNothing().when(smtpHandler).sendEmail(severeRecord);
        smtpHandler.publish(severeRecord);
        verify(smtpHandler, times(1)).sendEmail(severeRecord);
    }

    @Test
    void testFlushAndClose_DoNothing() {
        assertDoesNotThrow(() -> {
            smtpHandler.flush();
            smtpHandler.close();
        });
    }

    @Test
    void testLogFileContainsSevereMessage() throws Exception {
        LoggerManager.configureLogging();
        Logger logger = Logger.getLogger(LoggerManagerTest.class.getName());
        logger.severe("Це критична помилка, яка має потрапити в лог і на пошту");
        Thread.sleep(1000);
        List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("app.log"));
        boolean found = lines.stream().anyMatch(line -> line.contains("Це критична помилка"));
        assertTrue(found, "Лог-файл повинен містити severe-повідомлення");
    }
}
