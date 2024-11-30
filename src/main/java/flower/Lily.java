package flower;

public class Lily extends Flower {
    private boolean hasAroma;

    public Lily(String color, double price, int freshness, int stemLength, boolean hasAroma) {
        super(color, price, freshness, stemLength);
        this.hasAroma = hasAroma;
    }

    public String toString() {
        return "Лілія, колір: " + getColor() +
                ", ціна: " + getPrice() +
                ", свіжість: " + getFreshness() +
                " днів, довжина стебла: " + getStemLength() +
                " см, має аромат: " + (hasAroma ? "так" : "ні");
    }
}
