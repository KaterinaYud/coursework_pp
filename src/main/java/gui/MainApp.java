package gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp extends Application {
    private static final Logger logger = Logger.getLogger(MainApp.class.getName());

    @Override
    public void start(Stage primaryStage) {
        try {
            logger.info("Запуск головного вікна.");
            MainLayout layout = new MainLayout();
            Scene scene = new Scene(layout.getRoot(), 1000, 700);
            scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
            primaryStage.setTitle("Магазин квітів");
            primaryStage.setScene(scene);
            primaryStage.show();
            logger.info("Головне вікно відображено успішно.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Помилка під час запуску додатку", e);
        }
    }

    public static void main(String[] args) {
        utils.LoggerManager.configureLogging();
        launch(args);
    }
}

