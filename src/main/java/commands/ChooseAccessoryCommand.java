package commands;
import accessory.*;
import bouquet.Bouquet;
import color.Color;
import utils.AlertService;
import utils.InputService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ChooseAccessoryCommand implements Command {
    private static final Logger logger = Logger.getLogger(ChooseAccessoryCommand.class.getName());
    private final Bouquet bouquet;
    private final AlertService alertService;
    private final InputService inputService;

    public ChooseAccessoryCommand(Bouquet bouquet, AlertService alertService, InputService inputService) {
        this.bouquet = bouquet;
        this.alertService = alertService;
        this.inputService = inputService;
    }

    @Override
    public void execute() {
        logger.info("Почато виконання ChooseAccessoryCommand");
        if (bouquet.getAccessories().size() >= 3) {
            logger.warning("Спроба додати аксесуар перевищує ліміт у 3 штуки.");
            alertService.showInfo("Увага", "Максимальна кількість аксесуарів до букета — 3.");
            return;
        }

        List<String> options = Arrays.asList("Бантик", "Подарункова обгортка", "Стрічка");
        Optional<String> result = inputService.promptChoice("Вибір аксесуара", "Додайте аксесуар до Вашого букета!", "Тип аксесуара:", options);
        if (result.isPresent()) {
            String choice = result.get();
            try {
                double price = promptDouble("Ціна", "Введіть ціну аксесуара:");
                if (price < 0) {
                    logger.warning("Введено від’ємну ціну аксесуара: " + price);
                    alertService.showInfo("Помилка", "Ціна не може бути від'ємною.");
                    return;
                }

                Accessory accessory = null;
                switch (choice) {
                    case "Бантик":
                        int size = promptInt("Розмір бантика", "Введіть розмір (10, 20 або 30):");
                        if (size != 10 && size != 20 && size != 30) {
                            logger.warning("Некоректний розмір бантика: " + size);
                            alertService.showInfo("Помилка", "Розмір бантика має бути 10, 20 або 30.");
                            return;
                        }
                        accessory = new Bow(price, size);
                        break;

                    case "Подарункова обгортка":
                        String color = promptText("Колір", "Введіть колір обгортки:");
                        if (!Color.isValidColor(color)) {
                            logger.warning("Некоректний колір обгортки: " + color);
                            alertService.showInfo("Помилка", "Некоректний колір.");
                            return;
                        }
                        accessory = new GiftWrap(price, color);
                        break;

                    case "Стрічка":
                        int length = promptInt("Довжина стрічки", "Введіть довжину (см):");
                        if (length < 0) {
                            logger.warning("Введено від’ємну довжину стрічки: " + length);
                            alertService.showInfo("Помилка", "Довжина не може бути від'ємною.");
                            return;
                        }
                        accessory = new Ribbon(price, length);
                        break;
                }

                if (accessory != null) {
                    bouquet.addAccessory(accessory);
                    logger.info("Аксесуар додано до букета: " + accessory);
                    alertService.showInfo("Аксесуар", "Аксесуар додано до букета.");
                }

            } catch (NumberFormatException e) {
                logger.warning("Невірний формат числа при введенні: " + e.getMessage());
                alertService.showInfo("Помилка", "Невірний формат числа.");
            } catch (IllegalArgumentException e) {
                logger.warning("Користувач скасував введення: " + e.getMessage());
                alertService.showInfo("Скасовано", "Додавання аксесуару скасовано.");
            } catch (Exception e) {
                logger.severe("Невідома помилка при додаванні аксесуара: " + e.getMessage());
                alertService.showInfo("Критична помилка", "Виникла помилка при додаванні аксесуара.");
            }
        } else {
            logger.info("Користувач скасував вибір аксесуара.");
        }
    }

    private double promptDouble(String title, String content) {
        return inputService.promptText(title, content).map(Double::parseDouble).orElseThrow(NumberFormatException::new);
    }

    private int promptInt(String title, String content) {
        return inputService.promptText(title, content).map(Integer::parseInt).orElseThrow(NumberFormatException::new);
    }

    private String promptText(String title, String content) {
        return inputService.promptText(title, content).orElseThrow(() -> new IllegalArgumentException("Введення скасовано"));
    }
}
