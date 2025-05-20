package color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {

    @Test
    public void testValidColorUpperCase() {
        assertTrue(Color.isValidColor("ЧЕРВОНИЙ"));
        assertTrue(Color.isValidColor("БІЛИЙ"));
    }

    @Test
    public void testValidColorLowerCase() {
        assertTrue(Color.isValidColor("рожевий"));
        assertTrue(Color.isValidColor("зелений"));
    }

    @Test
    public void testValidColorMixedCase() {
        assertTrue(Color.isValidColor("Фіолетовий"));
        assertTrue(Color.isValidColor("ПоМараНЧеВий"));
    }

    @Test
    public void testInvalidColor() {
        assertFalse(Color.isValidColor("Райдужний"));
        assertFalse(Color.isValidColor("Прозорий"));
    }
}
