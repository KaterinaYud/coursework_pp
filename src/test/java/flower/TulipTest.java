package flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TulipTest {
    private Tulip tulip;

    @BeforeEach
    void setUp() {
        tulip = new Tulip("Тюльпан", "tulip", "жовтий", 60.0, 3, 30, "жовтий");
    }

    @Test
    void testTulipConstructor() {
        assertEquals("Тюльпан", tulip.getName());
        assertEquals("tulip", tulip.getType());
        assertEquals("жовтий", tulip.getColor());
        assertEquals(60.0, tulip.getPrice());
        assertEquals(3, tulip.getFreshness());
        assertEquals(30, tulip.getStemLength());
        assertEquals("жовтий", tulip.colorPattern());
    }

    @Test
    void testCopy() {
        Tulip copyTulip = (Tulip) tulip.copy();
        assertEquals(tulip.getName(), copyTulip.getName());
        assertEquals(tulip.getType(), copyTulip.getType());
        assertEquals(tulip.getColor(), copyTulip.getColor());
        assertEquals(tulip.getPrice(), copyTulip.getPrice());
        assertEquals(tulip.getFreshness(), copyTulip.getFreshness());
        assertEquals(tulip.getStemLength(), copyTulip.getStemLength());
        assertEquals(tulip.colorPattern(), copyTulip.colorPattern());
    }

    @Test
    void testToString() {
        String expectedString = "Тюльпан, колір: жовтий, ціна: 60.0, свіжість: 3 днів, довжина стебла: 30 см, колір візерунку: жовтий";
        assertEquals(expectedString, tulip.toString());
    }

    @Test
    void testColorPattern() {
        assertEquals("жовтий", tulip.colorPattern());
    }
}
