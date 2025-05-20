package utils;

import javafx.scene.control.Alert;

public interface AlertFactory {
    Alert createAlert(Alert.AlertType type);
}
