package flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LisianthusTest {
    private Lisianthus lisianthus;

    @BeforeEach
    void setUp() {
        lisianthus = new Lisianthus("Еустома", "lisianthus", "фіолетовий", 150.0, 6, 35, 8);
    }

    @Test
    void testLisianthusConstructor() {
        assertEquals("Еустома", lisianthus.getName());
        assertEquals("lisianthus", lisianthus.getType());
        assertEquals("фіолетовий", lisianthus.getColor());
        assertEquals(150.0, lisianthus.getPrice());
        assertEquals(6, lisianthus.getFreshness());
        assertEquals(35, lisianthus.getStemLength());
        assertEquals(8, lisianthus.scentIntensity());
    }

    @Test
    void testCopy() {
        Lisianthus copyLisianthus = (Lisianthus) lisianthus.copy();
        assertEquals(lisianthus.getName(), copyLisianthus.getName());
        assertEquals(lisianthus.getType(), copyLisianthus.getType());
        assertEquals(lisianthus.getColor(), copyLisianthus.getColor());
        assertEquals(lisianthus.getPrice(), copyLisianthus.getPrice());
        assertEquals(lisianthus.getFreshness(), copyLisianthus.getFreshness());
        assertEquals(lisianthus.getStemLength(), copyLisianthus.getStemLength());
        assertEquals(lisianthus.scentIntensity(), copyLisianthus.scentIntensity());
    }

    @Test
    void testToString() {
        String expectedString = "Еустома, колір: фіолетовий, ціна: 150.0, свіжість: 6 днів, довжина стебла: 35 см, інтенсивність запаху: 8";
        assertEquals(expectedString, lisianthus.toString());
    }

    @Test
    void testScentIntensity() {
        assertEquals(8, lisianthus.scentIntensity());
    }
}
