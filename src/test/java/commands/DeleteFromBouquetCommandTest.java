package commands;
import bouquet.Bouquet;
import flower.Flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.AlertService;
import static org.mockito.Mockito.*;

class DeleteFromBouquetCommandTest {
    private DeleteFromBouquetCommand command;
    private Bouquet bouquet;
    private Flower flower;
    private AlertService alertService;

    @BeforeEach
    void setUp() {
        bouquet = mock(Bouquet.class);
        flower = mock(Flower.class);
        alertService = mock(AlertService.class);
        command = new DeleteFromBouquetCommand(bouquet, flower, alertService);
    }

    @Test
    void testExecuteSuccess() {
        when(bouquet.getFlowers()).thenReturn(java.util.Arrays.asList(flower));
        doNothing().when(bouquet).removeFlower(flower);
        command.execute();
        verify(bouquet).removeFlower(flower);
        verify(alertService).showInfo(eq("Видалення квітки"), contains("Квітку видалено з букета"));
    }

    @Test
    void testExecuteFlowerNotInBouquet() {
        when(bouquet.getFlowers()).thenReturn(java.util.Collections.emptyList());
        command.execute();
        verify(alertService).showError(eq("Помилка видалення"), contains("Цієї квітки немає в букеті."));
    }

    @Test
    void testExecuteWithNullBouquet() {
        command = new DeleteFromBouquetCommand(null, flower, alertService);
        command.execute();
        verify(alertService).showError(eq("Помилка видалення"), contains("Цієї квітки немає в букеті."));
    }

    @Test
    void testExecuteWithNullFlower() {
        command = new DeleteFromBouquetCommand(bouquet, null, alertService);
        command.execute();
        verify(alertService).showError(eq("Помилка видалення"), contains("Цієї квітки немає в букеті."));
    }

    @Test
    void testExecuteWithFlowerNotInBouquet() {
        when(bouquet.getFlowers()).thenReturn(java.util.Collections.emptyList());
        command.execute();
        verify(alertService).showError(eq("Помилка видалення"), contains("Цієї квітки немає в букеті."));
    }

    @Test
    void testExecuteExceptionHandling() {
        when(bouquet.getFlowers()).thenThrow(new RuntimeException("Unexpected error"));
        command.execute();
        verify(alertService).showError(eq("Критична помилка"), eq("Unexpected error"));
    }
}
