package flower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChamomileTest {
    private Chamomile chamomile;

    @BeforeEach
    void setUp() {
        chamomile = new Chamomile("Ромашка", "chamomile", "білий", 50.0, 5, 30, 10);
    }

    @Test
    void testChamomileConstructor() {
        assertEquals("Ромашка", chamomile.getName());
        assertEquals("chamomile", chamomile.getType());
        assertEquals("білий", chamomile.getColor());
        assertEquals(50.0, chamomile.getPrice());
        assertEquals(5, chamomile.getFreshness());
        assertEquals(30, chamomile.getStemLength());
        assertEquals(10, chamomile.getPetals());
    }

    @Test
    void testCopy() {
        Chamomile copyChamomile = (Chamomile) chamomile.copy();
        assertEquals(chamomile.getName(), copyChamomile.getName());
        assertEquals(chamomile.getType(), copyChamomile.getType());
        assertEquals(chamomile.getColor(), copyChamomile.getColor());
        assertEquals(chamomile.getPrice(), copyChamomile.getPrice());
        assertEquals(chamomile.getFreshness(), copyChamomile.getFreshness());
        assertEquals(chamomile.getStemLength(), copyChamomile.getStemLength());
        assertEquals(chamomile.getPetals(), copyChamomile.getPetals());
    }

    @Test
    void testToString() {
        String expectedString = "Ромашка, колір: білий, ціна: 50.0, свіжість: 5 днів, довжина стебла: 30 см, кількість пелюсток: 10";
        assertEquals(expectedString, chamomile.toString());
    }

    @Test
    void testGetPetals() {
        assertEquals(10, chamomile.getPetals());
    }
}
