package commands;
import accessory.*;
import bouquet.Bouquet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.AlertService;
import utils.InputService;
import java.util.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ChooseAccessoryCommandTest {
    private Bouquet bouquet;
    private AlertService alertService;
    private InputService inputService;
    private ChooseAccessoryCommand command;

    @BeforeEach
    void setUp() {
        bouquet = mock(Bouquet.class);
        alertService = mock(AlertService.class);
        inputService = mock(InputService.class);
        command = new ChooseAccessoryCommand(bouquet, alertService, inputService);
    }

    @Test
    void testAccessoryLimitReached() {
        when(bouquet.getAccessories()).thenReturn(Arrays.asList(
                new Accessory(5.0), new Accessory(10.0), new Accessory(15.0)));
        command.execute();
        verify(alertService).showInfo(eq("Увага"), contains("Максимальна кількість аксесуарів"));
        verify(inputService, never()).promptChoice(any(), any(), any(), any());
    }

    @Test
    void testUserCancelsChoice() {
        when(bouquet.getAccessories()).thenReturn(Collections.emptyList());
        when(inputService.promptChoice(anyString(), anyString(), anyString(), anyList())).thenReturn(Optional.empty());
        command.execute();
        verify(alertService, never()).showInfo(any(), any());
        verify(bouquet, never()).addAccessory(any());
    }

    @Test
    void testAddGiftWrapWithInvalidColor() {
        when(bouquet.getAccessories()).thenReturn(Collections.emptyList());
        when(inputService.promptChoice(anyString(), anyString(), anyString(), anyList()))
                .thenReturn(Optional.of("Подарункова обгортка"));
        when(inputService.promptText(eq("Ціна"), anyString())).thenReturn(Optional.of("30"));
        when(inputService.promptText(eq("Колір"), anyString())).thenReturn(Optional.of("неіснуючий"));
        command.execute();
        verify(alertService).showInfo(eq("Помилка"), contains("Некоректний колір"));
        verify(bouquet, never()).addAccessory(any());
    }

    @Test
    void testAddRibbonWithNegativeLength() {
        when(bouquet.getAccessories()).thenReturn(Collections.emptyList());
        when(inputService.promptChoice(anyString(), anyString(), anyString(), anyList()))
                .thenReturn(Optional.of("Стрічка"));
        when(inputService.promptText(eq("Ціна"), anyString())).thenReturn(Optional.of("10"));
        when(inputService.promptText(eq("Довжина стрічки"), anyString())).thenReturn(Optional.of("-5"));
        command.execute();
        verify(alertService).showInfo(eq("Помилка"), contains("Довжина не може бути"));
        verify(bouquet, never()).addAccessory(any());
    }

    @Test
    void testInvalidPriceInput() {
        when(bouquet.getAccessories()).thenReturn(Collections.emptyList());
        when(inputService.promptChoice(anyString(), anyString(), anyString(), anyList()))
                .thenReturn(Optional.of("Бантик"));
        when(inputService.promptText(eq("Ціна"), anyString())).thenReturn(Optional.of("abc"));
        command.execute();
        verify(alertService).showInfo(eq("Помилка"), contains("формат числа"));
        verify(bouquet, never()).addAccessory(any());
    }

    @Test
    void testAddBowWithInvalidSize() {
        when(bouquet.getAccessories()).thenReturn(Collections.emptyList());
        when(inputService.promptChoice(anyString(), anyString(), anyString(), anyList()))
                .thenReturn(Optional.of("Бантик"));
        when(inputService.promptText(eq("Ціна"), anyString())).thenReturn(Optional.of("15"));
        when(inputService.promptText(eq("Розмір бантика"), anyString())).thenReturn(Optional.of("25"));
        command.execute();
        verify(alertService).showInfo(eq("Помилка"), contains("Розмір бантика має бути"));
        verify(bouquet, never()).addAccessory(any());
    }

    @Test
    void testAddBowSuccess() {
        when(bouquet.getAccessories()).thenReturn(Collections.emptyList());
        when(inputService.promptChoice(anyString(), anyString(), anyString(), anyList()))
                .thenReturn(Optional.of("Бантик"));
        when(inputService.promptText(eq("Ціна"), anyString())).thenReturn(Optional.of("12.5"));
        when(inputService.promptText(eq("Розмір бантика"), anyString())).thenReturn(Optional.of("20"));
        command.execute();
        verify(bouquet).addAccessory(any(Bow.class));
        verify(alertService).showInfo(eq("Аксесуар"), contains("додано"));
    }

    @Test
    void testAddRibbonSuccess() {
        when(bouquet.getAccessories()).thenReturn(Collections.emptyList());
        when(inputService.promptChoice(anyString(), anyString(), anyString(), anyList()))
                .thenReturn(Optional.of("Стрічка"));
        when(inputService.promptText(eq("Ціна"), anyString())).thenReturn(Optional.of("7.5"));
        when(inputService.promptText(eq("Довжина стрічки"), anyString())).thenReturn(Optional.of("50"));
        command.execute();
        verify(bouquet).addAccessory(any(Ribbon.class));
        verify(alertService).showInfo(eq("Аксесуар"), contains("додано"));
    }

    @Test
    void testNegativePriceInput() {
        when(bouquet.getAccessories()).thenReturn(Collections.emptyList());
        when(inputService.promptChoice(anyString(), anyString(), anyString(), anyList()))
                .thenReturn(Optional.of("Стрічка"));
        when(inputService.promptText(eq("Ціна"), anyString())).thenReturn(Optional.of("-10"));
        command.execute();
        verify(alertService).showInfo(eq("Помилка"), contains("не може бути від'ємною"));
        verify(bouquet, never()).addAccessory(any());
    }


    @Test
    void testUnknownExceptionIsHandled() {
        when(bouquet.getAccessories()).thenReturn(Collections.emptyList());
        when(inputService.promptChoice(anyString(), anyString(), anyString(), anyList()))
                .thenReturn(Optional.of("Стрічка"));
        when(inputService.promptText(eq("Ціна"), anyString())).thenThrow(new RuntimeException("Unexpected"));
        command.execute();
        verify(alertService).showInfo(eq("Критична помилка"), anyString());
        verify(bouquet, never()).addAccessory(any());
    }
}
