package utils;
import javafx.scene.control.Alert;

public class DefaultAlertFactory implements AlertFactory {
    @Override
    public Alert createAlert(Alert.AlertType type) {
        return new Alert(type);
    }
}
