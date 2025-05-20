package commands;
import flower.Flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.AlertService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class EditFlowerCommandTest {

    private Flower flower;
    private AlertService alertService;
    private EditFlowerCommand command;

    @BeforeEach
    void setUp() {
        flower = mock(Flower.class);
        alertService = mock(AlertService.class);
        command = new EditFlowerCommand(flower, "Червоний", 50.0, 10, 20, alertService);
    }

    @Test
    void testExecute() {
        when(flower.toString()).thenReturn("Червоний, 50.0, 10, 20");
        command.execute();
        verify(alertService).showInfo(eq("Редагування квітки"), contains("Квітку оновлено:\nЧервоний, 50.0, 10, 20"));
    }

    @Test
    void testExecuteWithException() {
        doThrow(new RuntimeException("Помилка редагування")).when(flower).setColor(anyString());
        command.execute();
        verify(alertService, never()).showInfo(anyString(), anyString());
    }
}
