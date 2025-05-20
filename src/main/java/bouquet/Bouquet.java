package bouquet;
import flower.Flower;
import accessory.Accessory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Bouquet {
    private static final Logger logger = Logger.getLogger(Bouquet.class.getName());
    private final List<Flower> flowers;
    private final List<Accessory> accessory;

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
        logger.info("Квітка додана до букета: " + flower);
    }

    public void removeFlower(Flower flower) {
        if (flowers.remove(flower)) {
            logger.info("Квітка видалена з букета: " + flower);
        } else {
            logger.warning("Квітку не знайдено в букеті: " + flower);
        }
    }

    public void addAccessory(Accessory accessory) {
        if (this.accessory.size() < 3) {
            this.accessory.add(accessory);
            logger.info("Аксесуар додано до букета: " + accessory);
        } else {
            logger.warning("Максимальна кількість аксесуарів досягнута (3). Аксесуар не додано: " + accessory);
        }
    }
}

