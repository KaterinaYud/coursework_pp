package main;
import flower.*;
import menu.Menu;
import bouquet.Bouquet;
import accessory.Accessory;
import utils.ReadFromFile;
import java.util.*;
import java.util.Scanner;
import utils.LoggerManager;
import java.util.logging.Logger;

public class Main {
    private static List<Accessory> accessoryList = new ArrayList<>();
    private static Bouquet bouquet = new Bouquet();
    private static Scanner scan = new Scanner(System.in);
    private static final Logger logger = LoggerManager.getLogger();

    public static void main(String[] args) {
        logger.info("Запуск програми...");
        List<Flower> flowerList = ReadFromFile.flowersFromFile("src/Flower.txt");
        if (flowerList.isEmpty()) {
            logger.warning("Файл з квітами порожній або не завантажено.");
            System.out.println("Не вдалося завантажити квіти з файлу або файл порожній.");
            return;
        }
        try {
            startMenu(flowerList);
        } catch (Exception e) {
            logger.severe("Критична помилка: " + e.getMessage());
        }
    }

    public static void startMenu(List<Flower> flowerList) {
        logger.info("Початок роботи меню.");
        Menu menu = new Menu(flowerList, accessoryList, bouquet, scan);
        String option;
        do {
            menu.displayMenu();
            System.out.print("Оберіть пункт меню: ");
            option = scan.next();
            logger.fine("Обрано пункт меню: " + option);
            menu.selectOption(option);
        } while (!option.equals("0"));
        logger.info("Завершення програми.");
        scan.close();
    }
}


