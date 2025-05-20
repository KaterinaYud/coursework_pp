package commands;
import bouquet.Bouquet;
import flower.Flower;
import utils.AlertService;
import java.util.logging.Logger;

public class DeleteFromBouquetCommand implements Command {
    private static final Logger logger = Logger.getLogger(DeleteFromBouquetCommand.class.getName());
    private final Bouquet bouquet;
    private final Flower flower;
    private final AlertService alertService;

    public DeleteFromBouquetCommand(Bouquet bouquet, Flower flower, AlertService alertService) {
        this.bouquet = bouquet;
        this.flower = flower;
        this.alertService = alertService;
    }

    @Override
    public void execute() {
        logger.info("Виконання видалення квітки з букета розпочато");
        try {
            if (bouquet != null && flower != null && bouquet.getFlowers().contains(flower)) {
                bouquet.removeFlower(flower);
                logger.info("Квітка видалена з букета: " + flower);
                alertService.showInfo("Видалення квітки", "Квітку видалено з букета:\n" + flower.getName());
            } else {
                logger.warning("Спроба видалити квітку, якої немає в букеті: " + flower);
                alertService.showError("Помилка видалення", "Цієї квітки немає в букеті.");
            }
        } catch (Exception e) {
            logger.severe("Помилка під час видалення квітки: " + e.getMessage());
            alertService.showError("Критична помилка", e.getMessage());
        }
    }
}


