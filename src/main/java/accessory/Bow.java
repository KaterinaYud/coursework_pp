package accessory;

public class Bow extends Accessory {
    private int size;

    public Bow(double price, int size) {
        super(price);
        this.size = size;
    }

    public String toString() {
        return "Бант, розмір: " + size + " см, ціна: " + getPrice() + " грн";
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
