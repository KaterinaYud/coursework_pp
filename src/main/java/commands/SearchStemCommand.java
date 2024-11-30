package commands;
import bouquet.Bouquet;
import flower.Flower;
import utils.LoggerManager;
import java.util.Scanner;
import java.util.logging.Logger;

public class SearchStemCommand implements Command {
    private Bouquet bouquet;
    private Scanner scanner;
    private static final Logger logger = LoggerManager.getLogger();

    public SearchStemCommand(Bouquet bouquet, Scanner scanner) {
        this.bouquet = bouquet;
        this.scanner = scanner;
    }

    public void execute() {
        logger.info("Розпочато пошук квітів за довжиною стебла.");
        int minLength = -1, maxLength = -1;
        while (minLength < 0) {
            System.out.print("Введіть мінімальну довжину з діапазону: ");
            minLength = scanner.nextInt();
            if (minLength < 0) {
                logger.warning("Користувач ввів некоректну мінімальну довжину.");
                System.out.println("Мінімальна довжина має бути додатньою. Спробуйте ще раз.");
            }
        }

        while (maxLength <= minLength) {
            System.out.print("Введіть максимальну довжину з діапазону: ");
            maxLength = scanner.nextInt();
            if (maxLength <= minLength) {
                logger.warning("Користувач ввів некоректну максимальну довжину.");
                System.out.println("Максимальна довжина повинна бути більшою за мінімальну. Спробуйте ще раз.");
            }
        }
        logger.info("Мінімальна довжина: " + minLength + ", максимальна довжина: " + maxLength);
        System.out.println("Квіти з довжиною стебла від " + minLength + " до " + maxLength + ":");
        boolean found = false;
        for (Flower flower : bouquet.getFlowers()) {
            if (flower.getStemLength() >= minLength && flower.getStemLength() <= maxLength) {
                System.out.println(flower);
                found = true;
            }
        }
        if (!found) {
            logger.info("Жодної квітки у вказаному діапазоні не знайдено.");
            System.out.println("У букеті немає квітів із заданим діапазоном стебла.");
        } else {
            logger.info("Пошук квітів за довжиною стебла завершено успішно.");
        }
    }
}
