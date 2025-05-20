package flower;

public class Rose extends Flower {
    private boolean hasSpikes;

    public Rose(String name, String type, String color, double price, int freshness, int stemLength, boolean hasSpikes) {
        super(name, type, color, price, freshness, stemLength);
        this.hasSpikes = hasSpikes;
    }

    @Override
    public Flower copy() {
        return new Rose(getName(), getType(), getColor(), getPrice(), getFreshness(), getStemLength(), hasSpikes);
    }

    public String toString() {
        return "Троянда, колір: " + getColor() +
                ", ціна: " + getPrice() +
                ", свіжість: " + getFreshness() +
                " днів, довжина стебла: " + getStemLength() +
                " см, має шипи: " + (hasSpikes ? "так" : "ні");
    }

    public boolean hasSpikes() {
        return hasSpikes;
    }
}
