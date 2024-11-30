package commands;
import accessory.Accessory;
import bouquet.Bouquet;
import flower.Flower;
import utils.LoggerManager;
import java.util.logging.Logger;

public class CalculatePriceCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger();
    private Bouquet bouquet;

    public CalculatePriceCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    public void execute() {
        try {
            double totalPrice = calculatePrice();
            System.out.println("Загальна вартість букета: " + totalPrice + " грн");
            logger.info("Розрахунок вартості виконано. Загальна вартість: " + totalPrice + " грн");
        } catch (Exception e) {
            logger.severe("Помилка під час розрахунку вартості: " + e.getMessage());
        }
    }

    public double calculatePrice() {
        double flowersPrice = bouquet.getFlowers().stream().mapToDouble(Flower::getPrice).sum();
        double accessoriesPrice = bouquet.getAccessories().stream().mapToDouble(Accessory::getPrice).sum();
        return flowersPrice + accessoriesPrice;
    }
}
