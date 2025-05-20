package flower;

public class TestFlower extends Flower {

    public TestFlower(String name, String type, String color, double price, int freshness, int stemLength) {
        super(name, type, color, price, freshness, stemLength);
    }

    @Override
    public Flower copy() {
        return new TestFlower(getName(), getType(), getColor(), getPrice(), getFreshness(), getStemLength());
    }

    @Override
    public String toString() {
        return "TestFlower: " + getName();
    }
}
