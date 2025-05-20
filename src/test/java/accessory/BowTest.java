package accessory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BowTest {

    @Test
    public void testConstructorSetsFieldsCorrectly() {
        Bow bow = new Bow(15.0, 25);
        assertEquals(15.0, bow.getPrice(), 0.001);
        assertEquals(25, bow.getSize());
    }

    @Test
    public void testSetSize() {
        Bow bow = new Bow(10.0, 20);
        bow.setSize(30);
        assertEquals(30, bow.getSize());
    }

    @Test
    public void testToString() {
        Bow bow = new Bow(12.5, 18);
        String expected = "Бант, розмір: 18 см, ціна: 12.5 грн";
        assertEquals(expected, bow.toString());
    }
}
