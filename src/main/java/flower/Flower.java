package flower;

public abstract class Flower {
    private String name;
    private String type;
    private String color;
    private double price;
    private int freshness;
    private int stemLength;

    public Flower(String name, String type, String color, double price, int freshness, int stemLength) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.price = price;
        this.freshness = freshness;
        this.stemLength = stemLength;
    }

    public abstract Flower copy();

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flower flower = (Flower) o;

        return Double.compare(flower.price, price) == 0 &&
                freshness == flower.freshness &&
                stemLength == flower.stemLength &&
                name.equals(flower.name) &&
                type.equals(flower.type) &&
                color.equals(flower.color);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + color.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + freshness;
        result = 31 * result + stemLength;
        return result;
    }
}
