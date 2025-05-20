package commands;
import bouquet.Bouquet;
import flower.Flower;
import utils.AlertService;
import java.util.logging.Logger;

public class AddToBouquetCommand implements Command {
    private static final Logger logger = Logger.getLogger(AddToBouquetCommand.class.getName());
    private final Bouquet bouquet;
    private final Flower flower;
    private final AlertService alertService;

    public AddToBouquetCommand(Bouquet bouquet, Flower flower, AlertService alertService) {
        this.bouquet = bouquet;
        this.flower = flower;
        this.alertService = alertService;
    }

    @Override
    public void execute() {
        logger.info("Виконання команди додавання квітки до букета розпочато");
        try {
            if (flower != null && bouquet != null) {
                bouquet.addFlower(flower.copy());
                logger.info("Квітка додана до букета: " + flower);
                alertService.showInfo("Додавання квітки", "Квітку додано до букета:\n" + flower.getName());
            } else {
                logger.severe("Спроба додати квітку, помилка: букет=" + bouquet + " квітка=" + flower);
                alertService.showError("Помилка", "Букет або квітка не визначені.");
            }
        } catch (Exception e) {
            logger.severe("Неочікувана помилка при додаванні квітки: " + e.getMessage());
            alertService.showError("Критична помилка", e.getMessage());
        }
    }
}
