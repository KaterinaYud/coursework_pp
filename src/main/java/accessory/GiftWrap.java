package accessory;

public class GiftWrap extends Accessory {
    private String color;

    public GiftWrap(double price, String color) {
        super(price);
        this.color = color;
    }

    public String toString() {
        return "Подарункова обгортка, колір: " + color + ", ціна: " + getPrice() + " грн";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

