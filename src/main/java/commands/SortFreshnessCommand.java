package commands;
import bouquet.Bouquet;
import flower.Flower;
import utils.LoggerManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Logger;

public class SortFreshnessCommand implements Command {
    private Bouquet bouquet;
    private static final Logger logger = LoggerManager.getLogger();

    public SortFreshnessCommand(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    public void execute() {
        logger.info("Розпочато сортування квітів за свіжістю.");
        if (bouquet.getFlowers().isEmpty()) {
            logger.warning("Букет порожній");
            System.out.println("Букет порожній. Немає квіток для сортування.");
        } else {
            ArrayList<Flower> sortedFlowers = new ArrayList<>(bouquet.getFlowers());
            sortedFlowers.sort(Comparator.comparing(Flower::getFreshness));
            logger.info("Сортування квітів за свіжістю завершено.");
            System.out.println("Квіти в букеті відсортовані за свіжістю:");
            for (int i = 0; i < sortedFlowers.size(); i++) {
                System.out.println((i + 1) + ". " + sortedFlowers.get(i));
            }
        }
    }
}
