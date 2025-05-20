package commands;
import bouquet.Bouquet;
import flower.Flower;
import utils.AlertService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortFreshnessCommand implements Command {
    private final Bouquet bouquet;
    private final AlertService alertService;
    private static final Logger logger = Logger.getLogger(SortFreshnessCommand.class.getName());

    public SortFreshnessCommand(Bouquet bouquet, AlertService alertService) {
        this.bouquet = bouquet;
        this.alertService = alertService;
    }

    @Override
    public void execute() {
        try {
            logger.info("Розпочато сортування квітів за свіжістю.");
            if (bouquet.getFlowers().isEmpty()) {
                logger.warning("Букет порожній");
                alertService.showInfo("Сортування", "Букет порожній. Немає квіток для сортування.");
            } else {
                ArrayList<Flower> sortedFlowers = new ArrayList<>(bouquet.getFlowers());
                sortedFlowers.sort(Comparator.comparing(Flower::getFreshness));
                StringBuilder result = new StringBuilder("Квіти в букеті відсортовані за свіжістю:\n");
                for (int i = 0; i < sortedFlowers.size(); i++) {
                    result.append(i + 1).append(". ").append(sortedFlowers.get(i)).append("\n");
                }
                logger.info("Сортування квітів завершено. Кількість квіток після сортування: " + sortedFlowers.size());
                alertService.showInfo("Сортування", result.toString());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Сталася помилка при сортуванні квіток", e);
            alertService.showError("Помилка", "Сталася помилка при сортуванні квіток.");
        }
    }
}

