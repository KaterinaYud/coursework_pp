package accessory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GiftWrapTest {

    @Test
    public void testConstructorSetsFieldsCorrectly() {
        GiftWrap wrap = new GiftWrap(8.0, "червоний");
        assertEquals(8.0, wrap.getPrice(), 0.001);
        assertEquals("червоний", wrap.getColor());
    }

    @Test
    public void testSetColor() {
        GiftWrap wrap = new GiftWrap(5.0, "синій");
        wrap.setColor("зелений");
        assertEquals("зелений", wrap.getColor());
    }

    @Test
    public void testToString() {
        GiftWrap wrap = new GiftWrap(9.99, "золотий");
        String expected = "Подарункова обгортка, колір: золотий, ціна: 9.99 грн";
        assertEquals(expected, wrap.toString());
    }
}
