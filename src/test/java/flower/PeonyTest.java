package flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PeonyTest {
    private Peony peony;

    @BeforeEach
    void setUp() {
        peony = new Peony("Піон", "piony", "рожевий", 120.0, 4, 45, true);
    }

    @Test
    void testPeonyConstructor() {
        assertEquals("Піон", peony.getName());
        assertEquals("piony", peony.getType());
        assertEquals("рожевий", peony.getColor());
        assertEquals(120.0, peony.getPrice());
        assertEquals(4, peony.getFreshness());
        assertEquals(45, peony.getStemLength());
        assertTrue(peony.hasAroma());
    }

    @Test
    void testCopy() {
        Peony copyPeony = (Peony) peony.copy();
        assertEquals(peony.getName(), copyPeony.getName());
        assertEquals(peony.getType(), copyPeony.getType());
        assertEquals(peony.getColor(), copyPeony.getColor());
        assertEquals(peony.getPrice(), copyPeony.getPrice());
        assertEquals(peony.getFreshness(), copyPeony.getFreshness());
        assertEquals(peony.getStemLength(), copyPeony.getStemLength());
        assertEquals(peony.hasAroma(), copyPeony.hasAroma());
    }

    @Test
    void testToString() {
        String expectedString = "Піон, колір: рожевий, ціна: 120.0, свіжість: 4 днів, довжина стебла: 45 см, має аромат: так";
        assertEquals(expectedString, peony.toString());
    }

    @Test
    void testHasAroma() {
        assertTrue(peony.hasAroma());
    }

    @Test
    void testHasNotAroma() {
        Peony peonyWithoutSpikes = new Peony("Піон", "piony", "білий", 90.0, 4, 45, false);
        assertFalse(peonyWithoutSpikes.hasAroma());
    }
}
