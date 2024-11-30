package commands;
import flower.Flower;
import utils.LoggerManager;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class DeleteFlowerCommand implements Command {
    private static final Logger logger = LoggerManager.getLogger();
    private List<Flower> flowerList;
    private Scanner scanner;

    public DeleteFlowerCommand(List<Flower> flowerList, Scanner scanner) {
        this.flowerList = flowerList;
        this.scanner = scanner;
    }

    public void execute() {
        if (flowerList.isEmpty()) {
            System.out.println("Список порожній, немає що видаляти.");
            logger.warning("Спроба видалити квітку з порожнього списку.");
            return;
        }

        System.out.println("Наявні квітки:");
        for (int i = 0; i < flowerList.size(); i++) {
            System.out.println((i + 1) + ". " + flowerList.get(i));
        }

        int index = -1;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Введіть номер квітки для видалення (1-" + flowerList.size() + "): ");
            try {
                index = scanner.nextInt();
                if (index >= 1 && index <= flowerList.size()) {
                    Flower removedFlower = flowerList.remove(index - 1);
                    System.out.println("Квітка видалена.");
                    logger.info("Квітка успішно видалена: " + removedFlower);
                    validInput = true;
                } else {
                    System.out.println("Некоректний номер квітки. Спробуйте ще раз.");
                    logger.warning("Введено некоректний номер квітки: " + index);
                }
            } catch (InputMismatchException e) {
                System.out.println("Будь ласка, введіть ціле число.");
                logger.warning("Помилка вводу: введено некоректний тип даних.");
                scanner.next();
            } catch (Exception e) {
                logger.severe("Помилка під час видалення квітки: " + e.getMessage());
            }
        }
    }
}
