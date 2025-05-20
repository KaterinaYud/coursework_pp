package flower;

public class Tulip extends Flower {
    private String colorPattern;

    public Tulip(String name, String type, String color, double price, int freshness, int stemLength, String colorPattern) {
        super(name, type, color, price, freshness, stemLength);
        this.colorPattern = colorPattern;
    }

    @Override
    public Flower copy() {
        return new Tulip(getName(), getType(), getColor(), getPrice(), getFreshness(), getStemLength(), colorPattern);
    }

    public String toString() {
        return "Тюльпан, колір: " + getColor() +
                ", ціна: " + getPrice() +
                ", свіжість: " + getFreshness() +
                " днів, довжина стебла: " + getStemLength() +
                " см, колір візерунку: " + colorPattern();
    }

    public String colorPattern() {
        return colorPattern;
    }
}
