package flower;

public abstract class Flower {
    private String color;
    private double price;
    private int freshness;
    private int stemLength;

    public Flower(String color, double price, int freshness, int stemLength) {
        this.color = color;
        this.price = price;
        this.freshness = freshness;
        this.stemLength = stemLength;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public int getFreshness() {
        return freshness;
    }

    public int getStemLength() {
        return stemLength;
    }

    public abstract String toString();

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFreshness(int freshness) {
        this.freshness = freshness;
    }

    public void setStemLength(int stemLength) {
        this.stemLength = stemLength;
    }
}
