package flower;

public class Chamomile extends Flower {
    private int petals;

    public Chamomile(String name, String type, String color, double price, int freshness, int stemLength, int petals) {
        super(name, type, color, price, freshness, stemLength);
        this.petals = petals;
    }

    @Override
    public Flower copy() {
        return new Chamomile(getName(), getType(), getColor(), getPrice(), getFreshness(), getStemLength(), petals);
    }

    public String toString() {
        return "Ромашка, колір: " + getColor() +
                ", ціна: " + getPrice() +
                ", свіжість: " + getFreshness() +
                " днів, довжина стебла: " + getStemLength() +
                " см, кількість пелюсток: " + petals;
    }

    public int getPetals() {
        return petals;
    }
}
