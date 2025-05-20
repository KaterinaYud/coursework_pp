package utils;
import javafx.scene.control.Alert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class FxAlertServiceTest {
    private FxAlertService alertService;
    private Alert mockAlert;

    @BeforeEach
    void setUp() {
        mockAlert = mock(Alert.class);
        AlertFactory mockFactory = mock(AlertFactory.class);
        when(mockFactory.createAlert(Alert.AlertType.INFORMATION)).thenReturn(mockAlert);
        when(mockFactory.createAlert(Alert.AlertType.ERROR)).thenReturn(mockAlert);
        alertService = new FxAlertService(mockFactory);
    }

    @Test
    void testShowError() {
        alertService.showError("ErrorTitle", "ErrorContent");
        verify(mockAlert).setTitle("ErrorTitle");
        verify(mockAlert).setContentText("ErrorContent");
        verify(mockAlert).showAndWait();
    }
}
