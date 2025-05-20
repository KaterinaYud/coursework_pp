package utils;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class FxInputServiceTest {

    private InputService inputService;

    @BeforeEach
    void setUp() {
        inputService = new FxInputService();
    }

    @Test
    void testPromptChoice_ShouldReturnUserChoice() {
        List<String> options = Arrays.asList("Option1", "Option2");
        try (MockedConstruction<ChoiceDialog> mocked = mockConstruction(ChoiceDialog.class,
                (mock, context) -> {
                    when(mock.showAndWait()).thenReturn(Optional.of("Option2"));
                })) {
            Optional<String> result = inputService.promptChoice("Title", "Header", "Content", options);
            ChoiceDialog<String> createdDialog = mocked.constructed().get(0);
            verify(createdDialog).setTitle("Title");
            verify(createdDialog).setHeaderText("Header");
            verify(createdDialog).setContentText("Content");
            assertTrue(result.isPresent());
            assertEquals("Option2", result.get());
        }
    }

    @Test
    void testPromptText_ShouldReturnUserInput() {
        try (MockedConstruction<TextInputDialog> mocked = mockConstruction(TextInputDialog.class,
                (mock, context) -> {
                    when(mock.showAndWait()).thenReturn(Optional.of("User input"));
                })) {
            Optional<String> result = inputService.promptText("Title", "Header");
            TextInputDialog createdDialog = mocked.constructed().get(0);
            verify(createdDialog).setTitle("Title");
            verify(createdDialog).setHeaderText("Header");
            assertTrue(result.isPresent());
            assertEquals("User input", result.get());
        }
    }
}
