package commands;
import bouquet.Bouquet;
import accessory.Accessory;
import flower.Flower;
import utils.LoggerManager;
import java.util.logging.Logger;

public class DisplayBouquetCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger();
    private Bouquet bouquet;

    public DisplayBouquetCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    public void execute() {
        logger.info("Розпочато відображення вмісту букета.");
        System.out.println("Ваш букет містить такі квіти та аксесуари:");
        if (!bouquet.getFlowers().isEmpty()) {
            System.out.println("Квіти:");
            for (int i = 0; i < bouquet.getFlowers().size(); i++) {
                Flower flower = bouquet.getFlowers().get(i);
                System.out.println((i + 1) + ". " + flower);
            }
            logger.info("Відображено " + bouquet.getFlowers().size() + " квіток у букеті.");
        } else {
            System.out.println("Букет не містить квітів.");
            logger.warning("Спроба відобразити квіти у порожньому букеті.");
        }

        if (!bouquet.getAccessories().isEmpty()) {
            System.out.println("Аксесуари:");
            for (int i = 0; i < bouquet.getAccessories().size(); i++) {
                Accessory accessory = bouquet.getAccessories().get(i);
                System.out.println((i + 1) + ". " + accessory.toString());
            }
            logger.info("Відображено " + bouquet.getAccessories().size() + " аксесуарів у букеті.");
        } else {
            System.out.println("Букет не містить аксесуарів.");
            logger.warning("Спроба відобразити аксесуари у порожньому букеті.");
        }
        logger.info("Завершено відображення вмісту букета.");
    }
}
