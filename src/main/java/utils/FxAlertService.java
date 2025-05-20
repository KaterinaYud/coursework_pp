package utils;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class FxAlertService implements AlertService {
    private final AlertFactory alertFactory;

    public FxAlertService() {
        this.alertFactory = new DefaultAlertFactory();
    }

    public FxAlertService(AlertFactory alertFactory) {
        this.alertFactory = alertFactory;
    }

    public void showInfo(String title, String message) {
        Alert alert = alertFactory.createAlert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        int threshold = 200;
        if (message.length() <= threshold) {
            alert.setContentText(message);
        } else {
            TextArea textArea = new TextArea(message);
            textArea.setWrapText(true);
            textArea.setEditable(false);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            VBox dialogPaneContent = new VBox(textArea);
            dialogPaneContent.setPadding(new Insets(10));
            alert.getDialogPane().setContent(dialogPaneContent);
        }
        alert.showAndWait();
    }

    @Override
    public void showError(String title, String content) {
        Alert alert = alertFactory.createAlert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
