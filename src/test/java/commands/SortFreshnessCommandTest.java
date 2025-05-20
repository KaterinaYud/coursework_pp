package commands;
import bouquet.Bouquet;
import flower.Flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.AlertService;
import java.util.Arrays;
import static org.mockito.Mockito.*;

public class SortFreshnessCommandTest {
    private AlertService alertService;
    private Bouquet bouquet;

    @BeforeEach
    public void setup() {
        alertService = mock(AlertService.class);
        bouquet = new Bouquet();
    }

    @Test
    public void testEmptyBouquet() {
        SortFreshnessCommand command = new SortFreshnessCommand(bouquet, alertService);
        command.execute();
        verify(alertService).showInfo("Сортування", "Букет порожній. Немає квіток для сортування.");
    }

    @Test
    public void testSortedBouquet() {
        Flower flower1 = mock(Flower.class);
        Flower flower2 = mock(Flower.class);
        when(flower1.getFreshness()).thenReturn(5);
        when(flower2.getFreshness()).thenReturn(2);
        when(flower1.toString()).thenReturn("Квітка 1");
        when(flower2.toString()).thenReturn("Квітка 2");

        bouquet.getFlowers().addAll(Arrays.asList(flower1, flower2));
        SortFreshnessCommand command = new SortFreshnessCommand(bouquet, alertService);
        command.execute();

        verify(alertService).showInfo(eq("Сортування"), contains("Квіти в букеті відсортовані"));
        verifyNoMoreInteractions(alertService);
    }

    @Test
    public void testExceptionHandling() {
        Bouquet faultyBouquet = mock(Bouquet.class);
        when(faultyBouquet.getFlowers()).thenThrow(new RuntimeException("Test Exception"));
        SortFreshnessCommand command = new SortFreshnessCommand(faultyBouquet, alertService);
        command.execute();
        verify(alertService).showError(eq("Помилка"), contains("Сталася помилка при сортуванні квіток."));
    }
}
