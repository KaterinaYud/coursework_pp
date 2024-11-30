package commands;
import bouquet.Bouquet;
import flower.Flower;
import utils.LoggerManager;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class AddToBouquetCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger();
    private Bouquet bouquet;
    private List<Flower> flowerList;
    private Scanner scanner;

    public AddToBouquetCommand(Bouquet bouquet, List<Flower> flowerList, Scanner scanner) {
        this.bouquet = bouquet;
        this.flowerList = flowerList;
        this.scanner = scanner;
    }

    public void execute() {
        logger.info("Розпочато виконання команди додавання квітки до букета.");
        int index;
        int attempts = 0;
        System.out.println("Оберіть квітку для додавання до букета:");
        for (int i = 0; i < flowerList.size(); i++) {
            System.out.println((i + 1) + ". " + flowerList.get(i));
        }
        while (true) {
            if (attempts >= 3) {
                logger.severe("Перевищено кількість спроб вибору квітки.");
                System.err.println("Помилка: занадто багато невдалих спроб вибору квітки.");
                return;
            }
            System.out.print("Введіть номер квітки для додавання до букета (1-" + flowerList.size() + "): ");
            index = scanner.nextInt() - 1;
            if (index >= 0 && index < flowerList.size()) {
                bouquet.addFlower(flowerList.get(index));
                logger.info("Квітка додана до букета: " + flowerList.get(index));
                System.out.println("Квітка додана до букета: " + flowerList.get(index));
                break;
            } else {
                attempts++;
                logger.warning("Користувач ввів некоректний номер квітки. Спроба " + attempts);
                System.out.println("Некоректний номер квітки. Спробуйте ще раз.");
            }
        }
    }
}
