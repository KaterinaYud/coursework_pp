package commands;
import bouquet.Bouquet;
import flower.Flower;
import utils.InputService;
import utils.AlertService;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchStemCommand implements Command {
    private final Bouquet bouquet;
    private final AlertService alertService;
    private final InputService inputService;
    private static final Logger logger = Logger.getLogger(SearchStemCommand.class.getName());

    public SearchStemCommand(Bouquet bouquet, AlertService alertService, InputService inputService) {
        this.bouquet = bouquet;
        this.alertService = alertService;
        this.inputService = inputService;
    }

    @Override
    public void execute() {
        logger.info("Користувач запускає пошук за довжиною стебла");
        try {
            Optional<String> minOpt = inputService.promptText("Введіть мінімальну довжину стебла", null);
            Optional<String> maxOpt = inputService.promptText("Введіть максимальну довжину стебла", null);

            if (!minOpt.isPresent() || !maxOpt.isPresent()) {
                alertService.showInfo("Помилка", "Ви не ввели значення для меж пошуку.");
                return;
            }

            String minStr = minOpt.get();
            String maxStr = maxOpt.get();

            try {
                int min = Integer.parseInt(minStr.trim());
                int max = Integer.parseInt(maxStr.trim());

                if (min < 0 || max <= min) {
                    alertService.showInfo("Помилка", "Некоректні межі. Мінімум >= 0, максимум > мінімум.");
                    return;
                }

                logger.info("Запуск пошуку з межами: " + min + "–" + max);
                performSearch(min, max);

            } catch (NumberFormatException ex) {
                alertService.showInfo("Помилка", "Введено недійсне число.");
            }

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Невідома помилка при пошуку за стеблом", ex);
        }
    }

    private void performSearch(int minLength, int maxLength) {
        try {
            StringBuilder result = new StringBuilder();
            boolean found = false;

            for (Flower flower : bouquet.getFlowers()) {
                int stem = flower.getStemLength();
                if (stem >= minLength && stem <= maxLength) {
                    result.append("- ").append(flower).append("\n");
                    found = true;
                }
            }

            if (!found) {
                logger.warning("Не знайдено квітів у діапазоні: " + minLength + "–" + maxLength);
                alertService.showInfo("Результат пошуку", "У букеті немає квітів із заданим діапазоном стебла.");
            } else {
                logger.info("Знайдено квіти у діапазоні: " + minLength + "–" + maxLength);
                alertService.showInfo("Результат пошуку", "Квіти з довжиною стебла від " + minLength + " до " + maxLength + ":\n" + result);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Сталася помилка під час пошуку квітів", e);
            alertService.showInfo("Помилка", "Сталася помилка при виконанні пошуку квітів.");
        }
    }
}
