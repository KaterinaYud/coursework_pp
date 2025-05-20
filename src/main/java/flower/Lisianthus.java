package flower;

public class Lisianthus extends Flower {
    private int scentIntensity;

    public Lisianthus(String name, String type, String color, double price, int freshness, int stemLength, int scentIntensity) {
        super(name, type, color, price, freshness, stemLength);
        this.scentIntensity = scentIntensity;
    }

    @Override
    public Flower copy() {
        return new Lisianthus(getName(), getType(), getColor(), getPrice(), getFreshness(), getStemLength(), scentIntensity);
    }

    public String toString() {
        return "Еустома, колір: " + getColor() +
                ", ціна: " + getPrice() +
                ", свіжість: " + getFreshness() +
                " днів, довжина стебла: " + getStemLength() +
                " см, інтенсивність запаху: " + scentIntensity();
    }

    public int scentIntensity() {
        return scentIntensity;
    }
}
