package commands;
import accessory.Accessory;
import bouquet.Bouquet;
import flower.Flower;
import utils.AlertService;
import java.util.logging.Logger;

public class CalculatePriceCommand implements Command {
    private static final Logger logger = Logger.getLogger(CalculatePriceCommand.class.getName());
    private final Bouquet bouquet;
    private final AlertService alertService;

    public CalculatePriceCommand(Bouquet bouquet, AlertService alertService) {
        this.bouquet = bouquet;
        this.alertService = alertService;
    }

    @Override
    public void execute() {
        try {
            double totalPrice = calculatePrice();
            logger.info("Розрахунок вартості виконано. Загальна вартість: " + totalPrice + " грн");

            alertService.showInfo("Ціна", "Загальна ціна букета:\n" + totalPrice + " грн");
        } catch (Exception e) {
            logger.severe("Помилка під час розрахунку вартості: " + e.getMessage());

            alertService.showError("Помилка", e.getMessage());
        }
    }

    public double calculatePrice() {
        double flowersPrice = bouquet.getFlowers().stream().mapToDouble(Flower::getPrice).sum();
        double accessoriesPrice = bouquet.getAccessories().stream().mapToDouble(Accessory::getPrice).sum();
        return flowersPrice + accessoriesPrice;
    }
}

