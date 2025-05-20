package accessory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RibbonTest {

    @Test
    public void testConstructorSetsFieldsCorrectly() {
        Ribbon ribbon = new Ribbon(6.5, 100);
        assertEquals(6.5, ribbon.getPrice(), 0.001);
        assertEquals(100, ribbon.getLength());
    }

    @Test
    public void testSetLength() {
        Ribbon ribbon = new Ribbon(3.0, 50);
        ribbon.setLength(75);
        assertEquals(75, ribbon.getLength());
    }

    @Test
    public void testToString() {
        Ribbon ribbon = new Ribbon(7.77, 80);
        String expected = "Стрічка, довжина: 80 см, ціна: 7.77 грн";
        assertEquals(expected, ribbon.toString());
    }
}
