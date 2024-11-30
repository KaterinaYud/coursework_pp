package commands;
import color.Color;
import flower.*;
import utils.InputUtils;
import utils.LoggerManager;
import java.util.List;
import java.util.logging.Logger;

public class AddFlowerCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger();
    private List<Flower> flowerList;

    public AddFlowerCommand(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }

    public void execute() {
        logger.info("Розпочато виконання команди додавання квітки.");
        int choice = 0;
        int attempts = 0;
        while (attempts < 3) {
            System.out.println("Оберіть тип квітки: ");
            System.out.println("1. Троянда");
            System.out.println("2. Лілія");
            System.out.println("3. Ромашка");
            choice = InputUtils.getValidInt("Ваш вибір: ");
            if (choice >= 1 && choice <= 3) {
                break;
            } else {
                attempts++;
                logger.warning("Користувач зробив некоректний вибір типу квітки. Спроба: " + attempts);
                System.out.println("Некоректний вибір квітки. Спробуйте ще раз.");
                if (attempts >= 3) {
                    logger.severe("Користувач перевищив ліміт спроб для вибору типу квітки.");
                    System.out.println("Ви перевищили максимальну кількість спроб вибору квітки. Спробуйте пізніше.");
                    return;
                }
            }
        }

        String color = InputUtils.getStringInput("Введіть колір квітки: ");
        while (!Color.isValidColor(color)) {
            logger.warning("Користувач ввів некоректний колір: " + color);
            System.out.println("Такого кольору, на жаль, немає. Оберіть інший колір.");
            color = InputUtils.getStringInput("Введіть колір квітки: ");
        }

        double price = InputUtils.getValidDouble("Введіть ціну квітки: ");
        while (price <= 0) {
            logger.warning("Користувач ввів некоректну ціну: " + price);
            System.out.println("Ціна має бути додатньою. Спробуйте ще раз.");
            price = InputUtils.getValidDouble("Введіть ціну квітки: ");
        }

        int freshnessday = InputUtils.getValidInt("Введіть свіжість квітки у днях (від 0 до 10): ");
        while (freshnessday < 0 || freshnessday > 10) {
            logger.warning("Користувач ввів некоректну свіжість: " + freshnessday);
            System.out.println("Свіжість квітки має бути від 0 до 10 днів. Спробуйте ще раз.");
            freshnessday = InputUtils.getValidInt("Введіть свіжість квітки у днях (від 0 до 10): ");
        }

        int stemlength = InputUtils.getValidInt("Введіть довжину стебла квітки у см (до 200): ");
        while (stemlength < 1 || stemlength > 200) {
            logger.warning("Користувач ввів некоректну довжину стебла: " + stemlength);
            System.out.println("Довжина стебла повинна бути від 1 до 200 см. Спробуйте ще раз.");
            stemlength = InputUtils.getValidInt("Введіть довжину стебла квітки у см (до 200): ");
        }

        Flower flower = null;
        switch (choice) {
            case 1:
                boolean hasSpikes = InputUtils.getValidBoolean("Чи має квітка шипи? (true/false): ");
                flower = new Rose(color, price, freshnessday, stemlength, hasSpikes);
                break;
            case 2:
                boolean hasAroma = InputUtils.getValidBoolean("Чи має квітка аромат? (true/false): ");
                flower = new Lily(color, price, freshnessday, stemlength, hasAroma);
                break;
            case 3:
                int petals = InputUtils.getValidInt("Введіть кількість пелюсток: ");
                flower = new Chamomile(color, price, freshnessday, stemlength, petals);
                break;
        }
        flowerList.add(flower);
        logger.info("Квітку успішно додано: " + flower);
        System.out.println("Квітку додано: " + flower);
    }
}
