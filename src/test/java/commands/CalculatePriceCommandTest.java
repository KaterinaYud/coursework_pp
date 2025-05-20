package commands;
import accessory.Accessory;
import bouquet.Bouquet;
import flower.Flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.AlertService;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

class CalculatePriceCommandTest {
    private AlertService alertService;
    private Bouquet bouquet;

    @BeforeEach
    void setUp() {
        alertService = mock(AlertService.class);
        bouquet = mock(Bouquet.class);
    }

    @Test
    void testExecute_showsCorrectPrice() {
        Flower flower1 = mock(Flower.class);
        Flower flower2 = mock(Flower.class);
        Accessory accessory1 = mock(Accessory.class);
        when(flower1.getPrice()).thenReturn(50.0);
        when(flower2.getPrice()).thenReturn(30.0);
        when(accessory1.getPrice()).thenReturn(20.0);
        List<Flower> flowers = new ArrayList<>();
        flowers.add(flower1);
        flowers.add(flower2);
        when(bouquet.getFlowers()).thenReturn(flowers);
        List<Accessory> accessories = new ArrayList<>();
        accessories.add(accessory1);
        when(bouquet.getAccessories()).thenReturn(accessories);
        CalculatePriceCommand command = new CalculatePriceCommand(bouquet, alertService);
        command.execute();
        verify(alertService).showInfo(eq("Ціна"), contains("100.0 грн"));
    }

    @Test
    void testExecute_showsErrorOnException() {
        when(bouquet.getFlowers()).thenThrow(new RuntimeException("Помилка доступу до квітів"));
        CalculatePriceCommand command = new CalculatePriceCommand(bouquet, alertService);
        command.execute();
        verify(alertService).showError(eq("Помилка"), contains("Помилка доступу"));
    }
}
