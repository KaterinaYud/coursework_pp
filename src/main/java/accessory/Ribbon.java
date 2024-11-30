package accessory;

public class Ribbon extends Accessory {
    private int length;

    public Ribbon(double price, int length) {
        super(price);
        this.length = length;
    }

    public String toString() {
        return "Стрічка, довжина: " + length + " см, ціна: " + getPrice() + " грн";
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
