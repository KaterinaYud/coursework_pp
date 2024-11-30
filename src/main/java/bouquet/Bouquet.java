package bouquet;
import flower.Flower;
import accessory.Accessory;
import java.util.ArrayList;
import java.util.List;

public class Bouquet {
    private List<Flower> flowers;
    private List<Accessory> accessory;

    public Bouquet() {
        this.flowers = new ArrayList<>();
        this.accessory = new ArrayList<>();
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public List<Accessory> getAccessories() {
        return accessory;
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public void removeFlower(Flower flower) {
        if (flowers.remove(flower)) {
            System.out.println(flower + " видалено з букета.");
        } else {
            System.out.println("Квітку не знайдено в букеті.");
        }
    }

    public void addAccessory(Accessory accessory) {
        if (this.accessory.size() < 3) {
            this.accessory.add(accessory);
        } else {
            System.out.println("Максимальна кількість аксесуарів досягнута (3). Аксесуар не додано.");
        }
    }
}

