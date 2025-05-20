package commands;
import bouquet.Bouquet;
import flower.Flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.AlertService;
import utils.InputService;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.Mockito.*;

class SearchStemCommandTest {
    private Bouquet bouquet;
    private AlertService alertService;
    private InputService inputService;
    private SearchStemCommand command;

    @BeforeEach
    void setUp() {
        bouquet = mock(Bouquet.class);
        alertService = mock(AlertService.class);
        inputService = mock(InputService.class);
        command = new SearchStemCommand(bouquet, alertService, inputService);
    }

    @Test
    void testExecute_validInput_found() {
        Flower flower1 = mock(Flower.class);
        when(flower1.getStemLength()).thenReturn(15);
        when(flower1.toString()).thenReturn("Троянда, 15 см");

        when(inputService.promptText(anyString(), isNull())).thenReturn(
                Optional.of("10"),
                Optional.of("20")
        );
        when(bouquet.getFlowers()).thenReturn(Collections.singletonList(flower1));

        command.execute();

        verify(alertService).showInfo(eq("Результат пошуку"), contains("Троянда, 15 см"));
    }

    @Test
    void testExecute_validInput_notFound() {
        Flower flower1 = mock(Flower.class);
        when(flower1.getStemLength()).thenReturn(5);

        when(inputService.promptText(anyString(), isNull())).thenReturn(
                Optional.of("10"),
                Optional.of("20")
        );
        when(bouquet.getFlowers()).thenReturn(Collections.singletonList(flower1));

        command.execute();

        verify(alertService).showInfo(eq("Результат пошуку"), contains("немає квітів"));
    }

    @Test
    void testExecute_invalidInput_nonInteger() {
        when(inputService.promptText(anyString(), isNull())).thenReturn(
                Optional.of("abc"),
                Optional.of("20")
        );

        command.execute();

        verify(alertService).showInfo(eq("Помилка"), contains("недійсне число"));
    }

    @Test
    void testExecute_invalidInput_wrongRange() {
        when(inputService.promptText(anyString(), isNull())).thenReturn(
                Optional.of("-5"),
                Optional.of("4")
        );

        command.execute();

        verify(alertService).showInfo(eq("Помилка"), contains("Некоректні межі"));
    }

    @Test
    void testExecute_inputMissing() {
        when(inputService.promptText(anyString(), isNull())).thenReturn(Optional.empty());

        command.execute();

        verify(alertService).showInfo(eq("Помилка"), contains("не ввели значення"));
    }

    @Test
    void testExecute_exceptionDuringSearch() {
        when(inputService.promptText(anyString(), isNull())).thenReturn(
                Optional.of("10"),
                Optional.of("20")
        );
        when(bouquet.getFlowers()).thenThrow(new RuntimeException("Test error"));

        command.execute();

        verify(alertService).showInfo(eq("Помилка"), contains("Сталася помилка"));
    }
}

