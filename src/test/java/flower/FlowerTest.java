package flower;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlowerTest {

    @Test
    public void testGettersAndSetters() {
        TestFlower flower = new TestFlower("Rose", "Garden", "Red", 50.0, 10, 30);

        assertEquals("Rose", flower.getName());
        assertEquals("Garden", flower.getType());
        assertEquals("Red", flower.getColor());
        assertEquals(50.0, flower.getPrice());
        assertEquals(10, flower.getFreshness());
        assertEquals(30, flower.getStemLength());

        flower.setName("Tulip");
        flower.setType("Field");
        flower.setColor("Yellow");
        flower.setPrice(25.0);
        flower.setFreshness(7);
        flower.setStemLength(20);

        assertEquals("Tulip", flower.getName());
        assertEquals("Field", flower.getType());
        assertEquals("Yellow", flower.getColor());
        assertEquals(25.0, flower.getPrice());
        assertEquals(7, flower.getFreshness());
        assertEquals(20, flower.getStemLength());
    }

    @Test
    public void testEqualsAndHashCode() {
        TestFlower f1 = new TestFlower("Lily", "Wild", "White", 30.0, 8, 25);
        TestFlower f2 = new TestFlower("Lily", "Wild", "White", 30.0, 8, 25);
        TestFlower f3 = new TestFlower("Rose", "Garden", "Red", 50.0, 10, 30);

        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
        assertNotEquals(f1, f3);
    }

    @Test
    public void testCopy() {
        TestFlower original = new TestFlower("Orchid", "Exotic", "Purple", 60.0, 9, 35);
        Flower copy = original.copy();

        assertEquals(original, copy);
        assertNotSame(original, copy);
    }

    @Test
    public void testToString() {
        TestFlower flower = new TestFlower("Chamomile", "Field", "White", 15.0, 5, 18);
        assertEquals("TestFlower: Chamomile", flower.toString());
    }

    @Test
    public void testEqualsWithNullAndDifferentClass() {
        TestFlower flower = new TestFlower("Sunflower", "Field", "Yellow", 20.0, 6, 25);
        assertNotEquals(null, flower);
        assertNotEquals("NotAFlower", flower);
    }

    @Test
    public void testSettersWithExtremeValues() {
        TestFlower flower = new TestFlower("Strange", "Rare", "Blue", 0.0, -5, -10);
        flower.setPrice(0.0);
        flower.setFreshness(-100);
        flower.setStemLength(-1);
        assertEquals(0.0, flower.getPrice());
        assertEquals(-100, flower.getFreshness());
        assertEquals(-1, flower.getStemLength());
    }
}
