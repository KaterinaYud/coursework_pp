package commands;
import color.Color;
import flower.Flower;
import utils.InputUtils;
import utils.LoggerManager;
import java.util.Scanner;
import java.util.logging.Logger;

public class EditFlowerCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger();
    private Flower flower;
    private Scanner scanner;

    public EditFlowerCommand(Flower flower, Scanner scanner) {
        this.flower = flower;
        this.scanner = scanner;
    }

    public void execute() {
        logger.info("Розпочато редагування квітки: " + flower);
        System.out.println("Початкова квітка: " + flower);
        String color = InputUtils.getStringInput("Введіть колір квітки: ");
        while (!Color.isValidColor(color)) {
            System.out.println("Такого кольору, на жаль, немає. Оберіть інший колір.");
            logger.warning("Некоректний колір: " + color);
            color = InputUtils.getStringInput("Введіть колір квітки: ");
        }
        flower.setColor(color);
        logger.info("Колір квітки змінено на: " + color);

        double price;
        while (true) {
            System.out.print("Оновлена ціна квітки: ");
            price = scanner.nextDouble();
            if (price > 0) {
                flower.setPrice(price);
                logger.info("Ціна квітки змінена на: " + price);
                break;
            } else {
                System.out.println("Ціна повинна бути додатньою. Спробуйте знову.");
                logger.warning("Некоректна ціна: " + price);
            }
        }

        int freshness;
        while (true) {
            System.out.print("Оновлена свіжість квітки (в днях): ");
            freshness = scanner.nextInt();
            if (freshness > 0) {
                flower.setFreshness(freshness);
                logger.info("Свіжість квітки змінена на: " + freshness + " днів.");
                break;
            } else {
                System.out.println("Свіжість квітки має бути додатньою. Спробуйте ще раз.");
                logger.warning("Некоректна свіжість: " + freshness);
            }
        }

        int stemLength;
        while (true) {
            System.out.print("Оновлена довжина стебла квітки (в см): ");
            stemLength = scanner.nextInt();
            if (stemLength > 0) {
                flower.setStemLength(stemLength);
                logger.info("Довжина стебла квітки змінена на: " + stemLength + " см.");
                break;
            } else {
                System.out.println("Довжина стебла має бути додатньою. Спробуйте знову.");
                logger.warning("Некоректна довжина стебла: " + stemLength);
            }
        }
        System.out.println("Оновлений стан квітки: " + flower);
        System.out.println("Квітку оновлено.");
        logger.info("Редагування квітки завершено. Оновлена квітка: " + flower);
    }
}
