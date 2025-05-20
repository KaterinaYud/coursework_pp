package flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LilyTest {
    private Lily lily;

    @BeforeEach
    void setUp() {
        lily = new Lily("Лілія", "lily", "білий", 100.0, 7, 40, true);
    }

    @Test
    void testLilyConstructor() {
        assertEquals("Лілія", lily.getName());
        assertEquals("lily", lily.getType());
        assertEquals("білий", lily.getColor());
        assertEquals(100.0, lily.getPrice());
        assertEquals(7, lily.getFreshness());
        assertEquals(40, lily.getStemLength());
        assertTrue(lily.isHasAroma());
    }

    @Test
    void testCopy() {
        Lily copyLily = (Lily) lily.copy();
        assertEquals(lily.getName(), copyLily.getName());
        assertEquals(lily.getType(), copyLily.getType());
        assertEquals(lily.getColor(), copyLily.getColor());
        assertEquals(lily.getPrice(), copyLily.getPrice());
        assertEquals(lily.getFreshness(), copyLily.getFreshness());
        assertEquals(lily.getStemLength(), copyLily.getStemLength());
        assertEquals(lily.isHasAroma(), copyLily.isHasAroma());
    }

    @Test
    void testToString() {
        String expectedString = "Лілія, колір: білий, ціна: 100.0, свіжість: 7 днів, довжина стебла: 40 см, має аромат: так";
        assertEquals(expectedString, lily.toString());
    }

    @Test
    void testIsHasAroma() {
        assertTrue(lily.isHasAroma());
    }
}
