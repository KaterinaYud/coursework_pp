package flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoseTest {
    private Rose rose;

    @BeforeEach
    void setUp() {
        rose = new Rose("Троянда", "rose", "червоний", 80.0, 5, 50, true);
    }

    @Test
    void testRoseConstructor() {
        assertEquals("Троянда", rose.getName());
        assertEquals("rose", rose.getType());
        assertEquals("червоний", rose.getColor());
        assertEquals(80.0, rose.getPrice());
        assertEquals(5, rose.getFreshness());
        assertEquals(50, rose.getStemLength());
        assertTrue(rose.hasSpikes());
    }

    @Test
    void testCopy() {
        Rose copyRose = (Rose) rose.copy();
        assertEquals(rose.getName(), copyRose.getName());
        assertEquals(rose.getType(), copyRose.getType());
        assertEquals(rose.getColor(), copyRose.getColor());
        assertEquals(rose.getPrice(), copyRose.getPrice());
        assertEquals(rose.getFreshness(), copyRose.getFreshness());
        assertEquals(rose.getStemLength(), copyRose.getStemLength());
        assertEquals(rose.hasSpikes(), copyRose.hasSpikes());
    }

    @Test
    void testToString() {
        String expectedString = "Троянда, колір: червоний, ціна: 80.0, свіжість: 5 днів, довжина стебла: 50 см, має шипи: так";
        assertEquals(expectedString, rose.toString());
    }

    @Test
    void testHasSpikes() {
        assertTrue(rose.hasSpikes());
    }

    @Test
    void testHasNotSpikes() {
        Rose roseWithoutSpikes = new Rose("Троянда", "rose", "білий", 90.0, 4, 45, false);
        assertFalse(roseWithoutSpikes.hasSpikes());
    }
}
