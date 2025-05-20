package flower;

public class Lily extends Flower {
    private boolean isHasAroma;

    public Lily(String name, String type, String color, double price, int freshness, int stemLength, boolean isHasAroma) {
        super(name, type, color, price, freshness, stemLength);
        this.isHasAroma = isHasAroma;
    }

    @Override
    public Flower copy() {
        return new Lily(getName(), getType(), getColor(), getPrice(), getFreshness(), getStemLength(), isHasAroma);
    }

    public String toString() {
        return "Лілія, колір: " + getColor() +
                ", ціна: " + getPrice() +
                ", свіжість: " + getFreshness() +
                " днів, довжина стебла: " + getStemLength() +
                " см, має аромат: " + (isHasAroma ? "так" : "ні");
    }

    public boolean isHasAroma() {
        return isHasAroma;
    }
}
