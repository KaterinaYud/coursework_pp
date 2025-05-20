package commands;
import bouquet.Bouquet;
import flower.Flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.AlertService;
import static org.mockito.Mockito.*;

class AddToBouquetCommandTest {
    private AddToBouquetCommand command;
    private Bouquet bouquet;
    private Flower flower;
    private AlertService alertService;

    @BeforeEach
    void setUp() {
        bouquet = mock(Bouquet.class);
        flower = mock(Flower.class);
        alertService = mock(AlertService.class);
        command = new AddToBouquetCommand(bouquet, flower, alertService);
    }

    @Test
    void testExecuteSuccess() {
        when(flower.copy()).thenReturn(flower);
        command.execute();
        verify(bouquet).addFlower(flower);
        verify(alertService).showInfo(eq("Додавання квітки"), contains("Квітку додано до букета"));
    }

    @Test
    void testExecuteWithNullBouquet() {
        command = new AddToBouquetCommand(null, flower, alertService);
        command.execute();
        verify(alertService).showError(eq("Помилка"), contains("Букет або квітка не визначені."));
    }

    @Test
    void testExecuteWithNullFlower() {
        command = new AddToBouquetCommand(bouquet, null, alertService);
        command.execute();
        verify(alertService).showError(eq("Помилка"), contains("Букет або квітка не визначені."));
    }

    @Test
    void testExecuteWithNullFlowerAndBouquet() {
        command = new AddToBouquetCommand(null, null, alertService);
        command.execute();
        verify(alertService).showError(eq("Помилка"), contains("Букет або квітка не визначені."));
    }

    @Test
    void testExecuteThrowsException() {
        when(flower.copy()).thenThrow(new RuntimeException("Test exception"));
        command.execute();
        verify(alertService).showError(eq("Критична помилка"), contains("Test exception"));
    }
}
