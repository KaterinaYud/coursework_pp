package accessory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccessoryTest {

    @Test
    public void testConstructorSetsPriceCorrectly() {
        Accessory accessory = new Accessory(99.99);
        assertEquals(99.99, accessory.getPrice(), 0.001);
    }

    @Test
    public void testGetPrice() {
        Accessory accessory = new Accessory(45.50);
        assertEquals(45.50, accessory.getPrice(), 0.001);
    }

    @Test
    public void testSetPrice() {
        Accessory accessory = new Accessory(20.0);
        accessory.setPrice(55.55);
        assertEquals(55.55, accessory.getPrice(), 0.001);
    }

    @Test
    public void testSetPriceToZero() {
        Accessory accessory = new Accessory(20.0);
        accessory.setPrice(0.0);
        assertEquals(0.0, accessory.getPrice(), 0.001);
    }
}
