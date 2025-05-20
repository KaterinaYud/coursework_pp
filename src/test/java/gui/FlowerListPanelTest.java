package gui;
import bouquet.Bouquet;
import flower.Flower;
import flower.TestFlower;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;

public class FlowerListPanelTest extends ApplicationTest {
    private VBox centerPanel;
    private FlowerListPanel flowerListPanel;
    private Bouquet bouquet;

    @Override
    public void start(Stage stage) {
        centerPanel = new VBox();
        bouquet = new Bouquet();
        flowerListPanel = new FlowerListPanel(centerPanel, bouquet);
        VBox root = new VBox(flowerListPanel.getScrollPane(), centerPanel);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    @Test
    public void testScrollPaneExists() {
        ScrollPane pane = flowerListPanel.getScrollPane();
        assertNotNull(pane);
    }

    @Test
    public void testFlowerListPopulated() {
        ScrollPane pane = flowerListPanel.getScrollPane();
        VBox listBox = (VBox) pane.getContent();
        assertTrue(listBox.getChildren().size() > 1);
    }

    @Test
    public void testClickOnFlowerOpensDetails() {
        ScrollPane pane = flowerListPanel.getScrollPane();
        VBox listBox = (VBox) pane.getContent();
        VBox flowerItem = (VBox) listBox.getChildren().get(1);
        clickOn(flowerItem);
        verifyThat(".label", node -> node.isVisible());
        verifyThat(".button", node -> node.isVisible());
        assertFalse(centerPanel.getChildren().isEmpty());
    }

    @Test
    public void testAddToBouquetButtonWorks() {
        VBox listBox = (VBox) flowerListPanel.getScrollPane().getContent();
        VBox flowerItem = (VBox) listBox.getChildren().get(1);
        clickOn(flowerItem);
        clickOn("Додати до букета");
        assertEquals(1, bouquet.getFlowers().size());
    }

    @Test
    public void testDeleteFromBouquetButtonWorks() {
        Flower flower = new TestFlower("Троянда", "Rose", "Red", 100, 7, 50);
        bouquet.addFlower(flower);
        interact(() -> flowerListPanel.getScrollPane().getContent());
        interact(() -> flowerListPanel.getScrollPane().getContent());
        interact(() -> flowerListPanel.getScrollPane().getContent());
        interact(() -> flowerListPanel.getScrollPane().getContent());
        interact(() -> flowerListPanel.getScrollPane().getContent());
    }

    @Test
    public void testEditFlowerValidationFailsForInvalidInput() {
        VBox listBox = (VBox) flowerListPanel.getScrollPane().getContent();
        VBox flowerItem = (VBox) listBox.getChildren().get(1);
        clickOn(flowerItem);
        clickOn("Редагувати квітку");
        clickOn("Ціна:");
        write("-100");
        clickOn("Свіжість:");
        write("-10");
        clickOn("Зберегти");
        assertTrue(bouquet.getFlowers().isEmpty());
    }
}
