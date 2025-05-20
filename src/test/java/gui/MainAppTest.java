package gui;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

public class MainAppTest extends ApplicationTest {
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        new MainApp().start(stage);
    }

    @Test
    public void testWindowTitle() {
        assertEquals("Магазин квітів", stage.getTitle());
    }
}
