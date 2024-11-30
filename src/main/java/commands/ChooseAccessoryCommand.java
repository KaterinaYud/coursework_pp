package commands;
import accessory.*;
import bouquet.Bouquet;
import color.Color;
import utils.InputUtils;
import utils.LoggerManager;
import java.util.List;
import java.util.logging.Logger;

public class ChooseAccessoryCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger();
    private List<Accessory> accessoryList;
    private Bouquet bouquet;

    public ChooseAccessoryCommand(List<Accessory> accessoryList, Bouquet bouquet) {
        this.accessoryList = accessoryList;
        this.bouquet = bouquet;
    }

    public void execute() {
        if (bouquet.getAccessories().size() >= 3) {
            System.out.println("Максимальна кількість аксесуарів до букета — 3. Будь ласка, оберіть іншу опцію.");
            logger.warning("Спроба додати більше ніж 3 аксесуари до букета.");
            return;
        }
        int choice = 0;
        int attempts = 0;
        try {
            while (attempts < 3) {
                System.out.println("Введіть номер аксесуара: ");
                System.out.println("1. Бантик");
                System.out.println("2. Подарункова обгортка");
                System.out.println("3. Стрічка");
                choice = InputUtils.getValidInt("Ваш вибір: ");

                if (choice >= 1 && choice <= 3) {
                    logger.info("Користувач обрав тип аксесуара: " + choice);
                    break;
                } else {
                    attempts++;
                    if (attempts >= 3) {
                        logger.severe("Користувач некоректно вводить аксесуар вже тричі");
                        System.out.println("Ви тричі зробили некоректний вибір. Завершення.");
                        return;
                    }
                    System.out.println("Некоректний вибір аксесуара. Спробуйте ще раз.");
                    logger.warning("Некоректний вибір аксесуара. Спроба " + attempts);
                }
            }

            double price = InputUtils.getValidDouble("Введіть ціну аксесуара: ");
            while (price < 0) {
                System.out.println("Ціна не може бути від'ємною. Введіть коректну ціну.");
                logger.warning("Введено від'ємну ціну аксесуара.");
                price = InputUtils.getValidDouble("Введіть ціну аксесуара: ");
            }

            Accessory accessory = null;
            switch (choice) {
                case 1:
                    int size = InputUtils.getValidInt("Введіть розмір бантика (10, 20 або 30): ");
                    while (size != 10 && size != 20 && size != 30) {
                        System.out.println("Розмір бантика повинен бути 10, 20 або 30. Спробуйте ще раз.");
                        logger.warning("Некоректний розмір бантика: " + size);
                        size = InputUtils.getValidInt("Введіть розмір бантика (10, 20 або 30): ");
                    }
                    accessory = new Bow(price, size);
                    break;
                case 2:
                    String color = InputUtils.getStringInput("Введіть колір обгортки: ");
                    while (!Color.isValidColor(color)) {
                        System.out.println("Такого кольору, на жаль, немає. Оберіть інший колір.");
                        logger.warning("Некоректний колір обгортки: " + color);
                        color = InputUtils.getStringInput("Введіть колір обгортки: ");
                    }
                    accessory = new GiftWrap(price, color);
                    break;
                case 3:
                    int length = InputUtils.getValidInt("Введіть довжину стрічки (см): ");
                    while (length < 0) {
                        System.out.println("Довжина не може бути від'ємною. Введіть коректну довжину.");
                        logger.warning("Некоректна довжина стрічки: " + length);
                        length = InputUtils.getValidInt("Введіть довжину стрічки (см): ");
                    }
                    accessory = new Ribbon(price, length);
                    break;
            }
            bouquet.addAccessory(accessory);
            System.out.println("Аксесуар додано: " + accessory.getPrice() + " грн");
            logger.info("Аксесуар успішно додано: " + accessory);
        } catch (Exception e) {
            logger.severe("Помилка під час вибору аксесуара: " + e.getMessage());
        }
    }
}
