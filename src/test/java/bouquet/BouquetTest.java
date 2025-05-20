package bouquet;
import flower.TestFlower;
import accessory.Bow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BouquetTest {
    private Bouquet bouquet;
    private TestFlower flower1;
    private TestFlower flower2;

    @BeforeEach
    public void setUp() {
        bouquet = new Bouquet();
        flower1 = new TestFlower("Троянда", "Rose", "Red", 50.0, 10, 30);
        flower2 = new TestFlower("Тюльпан", "Tulip", "Yellow", 25.0, 7, 20);
    }

    @Test
    public void testAddAndRemoveFlower() {
        assertTrue(bouquet.getFlowers().isEmpty());

        bouquet.addFlower(flower1);
        bouquet.addFlower(flower2);
        assertEquals(2, bouquet.getFlowers().size());

        bouquet.removeFlower(flower1);
        assertEquals(1, bouquet.getFlowers().size());

        bouquet.removeFlower(flower1);
        assertEquals(1, bouquet.getFlowers().size());
    }

    @Test
    public void testAddAccessoryLimit() {
        assertTrue(bouquet.getAccessories().isEmpty());

        bouquet.addAccessory(new Bow(5.0, 10));
        bouquet.addAccessory(new Bow(6.0, 12));
        bouquet.addAccessory(new Bow(7.0, 14));

        assertEquals(3, bouquet.getAccessories().size());

        bouquet.addAccessory(new Bow(8.0, 16));
        assertEquals(3, bouquet.getAccessories().size());
    }
}
