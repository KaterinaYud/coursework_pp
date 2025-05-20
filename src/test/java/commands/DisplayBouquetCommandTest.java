package commands;
import bouquet.Bouquet;
import flower.Flower;
import accessory.Accessory;
import utils.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.util.ArrayList;

class DisplayBouquetCommandTest {
    private DisplayBouquetCommand command;
    private Bouquet bouquet;
    private AlertService alertService;

    @BeforeEach
    void setUp() {
        bouquet = mock(Bouquet.class);
        alertService = mock(AlertService.class);
        command = new DisplayBouquetCommand(bouquet, alertService);
    }

    @Test
    void testExecuteWithValidBouquet() {
        Flower flower = mock(Flower.class);
        when(flower.toString()).thenReturn("Троянда");
        Accessory accessory = mock(Accessory.class);
        when(accessory.toString()).thenReturn("Стрічка");
        ArrayList<Flower> flowers = new ArrayList<>();
        flowers.add(flower);
        ArrayList<Accessory> accessories = new ArrayList<>();
        accessories.add(accessory);
        when(bouquet.getFlowers()).thenReturn(flowers);
        when(bouquet.getAccessories()).thenReturn(accessories);
        command.execute();
        verify(alertService).showInfo(
                eq("Букет"),
                eq("Квіти:\n- Троянда\n\nАксесуари:\n- Стрічка\n")
        );
    }

    @Test
    void testExecuteWithNullBouquet() {
        DisplayBouquetCommand command = new DisplayBouquetCommand(null, alertService);
        command.execute();
        verify(alertService).showError(
                eq("Помилка"),
                eq("Неможливо відобразити вміст. Букет не ініціалізований.")
        );
    }

    @Test
    void testExecuteWithEmptyBouquet() {
        ArrayList<Flower> flowers = new ArrayList<>();
        ArrayList<Accessory> accessories = new ArrayList<>();
        when(bouquet.getFlowers()).thenReturn(flowers);
        when(bouquet.getAccessories()).thenReturn(accessories);
        command.execute();
        verify(alertService).showInfo(
                eq("Букет"),
                eq("Букет не містить квіточок\n\nБукет не містить аксесуарів.")
        );
    }

    @Test
    void testExecuteWithException() {
        when(bouquet.getFlowers()).thenThrow(new RuntimeException("Unexpected error"));
        command.execute();
        verify(alertService).showError(
                eq("Помилка"),
                eq("Unexpected error")
        );
    }
}
