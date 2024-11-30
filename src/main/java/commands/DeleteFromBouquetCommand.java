package commands;
import bouquet.Bouquet;
import flower.Flower;
import utils.LoggerManager;
import java.util.Scanner;
import java.util.logging.Logger;

public class DeleteFromBouquetCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger();
    private Bouquet bouquet;
    private Scanner scanner;

    public DeleteFromBouquetCommand(Bouquet bouquet, Scanner scanner) {
        this.bouquet = bouquet;
        this.scanner = scanner;
    }

    public void execute() {
        if (bouquet.getFlowers().isEmpty()) {
            System.out.println("Букет порожній");
            logger.warning("Спроба видалити квітку з порожнього букета.");
            return;
        }

        System.out.println("Квіти у букеті:");
        for (int i = 0; i < bouquet.getFlowers().size(); i++) {
            System.out.println((i + 1) + ". " + bouquet.getFlowers().get(i));
        }
        int index = -1;
        while (true) {
            System.out.print("Введіть номер квітки для видалення (1-" + bouquet.getFlowers().size() + "): ");
            if (scanner.hasNextInt()) {
                index = scanner.nextInt() - 1;
                if (index >= 0 && index < bouquet.getFlowers().size()) {
                    Flower removedFlower = bouquet.getFlowers().get(index);
                    bouquet.removeFlower(removedFlower);
                    System.out.println("Квітка видалена з букета.");
                    logger.info("Квітка успішно видалена з букета: " + removedFlower);
                    break;
                } else {
                    System.out.println("Некоректний номер. Спробуйте ще раз.");
                    logger.warning("Введено некоректний номер квітки: " + (index + 1));
                }
            } else {
                System.out.println("Будь ласка, введіть числове значення.");
                logger.warning("Помилка вводу: введено некоректний тип даних.");
                scanner.next();
            }
        }
    }
}
