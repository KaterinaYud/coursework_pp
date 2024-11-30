package flower;

public class Rose extends Flower {
    private boolean hasSpikes;

    public Rose(String color, double price, int freshness, int stemLength, boolean hasSpikes) {
        super(color, price, freshness, stemLength);
        this.hasSpikes = hasSpikes;
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
