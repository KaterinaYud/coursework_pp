package commands;
import bouquet.Bouquet;
import accessory.Accessory;
import flower.Flower;
import utils.AlertService;
import java.util.logging.Logger;

public class DisplayBouquetCommand implements Command {
    private static final Logger logger = Logger.getLogger(DisplayBouquetCommand.class.getName());
    private final Bouquet bouquet;
    private final AlertService alertService;

    public DisplayBouquetCommand(Bouquet bouquet, AlertService alertService) {
        this.bouquet = bouquet;
        this.alertService = alertService;
    }

    @Override
    public void execute() {
        logger.info("Розпочато відображення вмісту букета.");
        try {
            if (bouquet == null) {
                logger.severe("Об'єкт букет не ініціалізований (null).");
                alertService.showError("Помилка", "Неможливо відобразити вміст. Букет не ініціалізований.");
                return;
            }
            StringBuilder message = new StringBuilder();
            if (!bouquet.getFlowers().isEmpty()) {
                message.append("Квіти:\n");
                for (Flower flower : bouquet.getFlowers()) {
                    message.append("- ").append(flower.toString()).append("\n");
                }
                logger.info("Відображено " + bouquet.getFlowers().size() + " квіток у букеті.");
            } else {
                message.append("Букет не містить квіточок\n");
                logger.warning("Спроба відобразити квіти у порожньому букеті.");
            }
            if (!bouquet.getAccessories().isEmpty()) {
                message.append("\nАксесуари:\n");
                for (Accessory accessory : bouquet.getAccessories()) {
                    message.append("- ").append(accessory.toString()).append("\n");
                }
                logger.info("Відображено " + bouquet.getAccessories().size() + " аксесуарів у букеті.");
            } else {
                message.append("\nБукет не містить аксесуарів.");
                logger.warning("Спроба відобразити аксесуари у порожньому букеті.");
            }
            logger.info("Завершено відображення вмісту букета.");
            alertService.showInfo("Букет", message.toString());
        } catch (Exception e) {
            logger.severe("Помилка при відображенні букета: " + e.getMessage());
            alertService.showError("Помилка", e.getMessage());
        }
    }
}
