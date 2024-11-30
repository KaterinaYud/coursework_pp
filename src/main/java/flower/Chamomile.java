package flower;

public class Chamomile extends Flower {
    private int petals;

    public Chamomile(String color, double price, int freshness, int stemLength, int petals) {
        super(color, price, freshness, stemLength);
        this.petals = petals;
    }

    public String toString() {
        return "Ромашка, колір: " + getColor() +
                ", ціна: " + getPrice() +
                ", свіжість: " + getFreshness() +
                " днів, довжина стебла: " + getStemLength() +
                " см, кількість пелюсток: " + petals;
    }
}
