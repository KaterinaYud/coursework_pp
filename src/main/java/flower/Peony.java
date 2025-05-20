package flower;

public class Peony extends Flower {
    private boolean hasAroma;

    public Peony(String name, String type, String color, double price, int freshness, int stemLength, boolean hasAroma) {
        super(name, type, color, price, freshness, stemLength);
        this.hasAroma = hasAroma;
    }

    @Override
    public Flower copy() {
        return new Peony(getName(), getType(), getColor(), getPrice(), getFreshness(), getStemLength(), hasAroma);
    }

    public String toString() {
        return "Піон, колір: " + getColor() +
                ", ціна: " + getPrice() +
                ", свіжість: " + getFreshness() +
                " днів, довжина стебла: " + getStemLength() +
                " см, має аромат: " + (hasAroma ? "так" : "ні");
    }

    public boolean hasAroma() {
        return hasAroma;
    }
}
