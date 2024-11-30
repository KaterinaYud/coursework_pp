package menu;
import commands.*;
import bouquet.Bouquet;
import flower.Flower;
import accessory.Accessory;
import utils.LoggerManager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class Menu {
    private Map<String, MenuItem> menuItems;
    private List<Flower> flowerList;
    private Scanner scanner;
    private static final Logger logger = LoggerManager.getLogger();

    public Menu(List<Flower> flowerList, List<Accessory> accessoryList, Bouquet bouquet, Scanner scanner) {
        this.flowerList = flowerList;
        this.scanner = scanner;
        menuItems = new LinkedHashMap<>();
        menuItems.put("1", new MenuItem("Додати квітку", new AddFlowerCommand(flowerList)));
        menuItems.put("2", new MenuItem("Редагувати квітку", null));
        menuItems.put("3", new MenuItem("Видалити квітку", new DeleteFlowerCommand(flowerList, scanner)));
        menuItems.put("4", new MenuItem("Додати квітку до букета", new AddToBouquetCommand(bouquet, flowerList, scanner)));
        menuItems.put("5", new MenuItem("Видалити квітку з букета", new DeleteFromBouquetCommand(bouquet, scanner)));
        menuItems.put("6", new MenuItem("Розрахувати ціну", new CalculatePriceCommand(bouquet)));
        menuItems.put("7", new MenuItem("Обрати аксесуар", new ChooseAccessoryCommand(accessoryList, bouquet)));
        menuItems.put("8", new MenuItem("Сортувати за свіжістю", new SortFreshnessCommand(bouquet)));
        menuItems.put("9", new MenuItem("Знайти за стеблом", new SearchStemCommand(bouquet, scanner)));
        menuItems.put("10", new MenuItem("Вивести отриманий букет", new DisplayBouquetCommand(bouquet)));
        menuItems.put("0", new MenuItem("Вихід з програми", null));
    }

    public void displayMenu() {
        logger.fine("Відображення меню користувачу.");
        System.out.println("\n*** ЗРОБІТЬ ВИБІР ***");
        for (Map.Entry<String, MenuItem> entry : menuItems.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getDescription());
        }
    }

    public void selectOption(String option) {
        logger.fine("Користувач обрав опцію: " + option);
        MenuItem menuItem = menuItems.get(option);
        if (menuItem != null) {
            if (option.equals("0")) {
                logger.info("Користувач завершив роботу.");
                System.out.println("Вихід з програми.");
            } else if (option.equals("2")) {
                Flower flower = selectFlower();
                new EditFlowerCommand(flower, scanner).execute();
                logger.info("Редагування квітки виконано.");
            } else {
                menuItem.execute();
                logger.info("Команда виконана: " + menuItem.getDescription());
            }
        } else {
            logger.warning("Некоректний вибір меню: " + option);
            System.out.println("Некоректний вибір, спробуйте знову.");
        }
    }

    public Flower selectFlower() {
        System.out.println("Список доступних квіток:");
        for (int i = 0; i < flowerList.size(); i++) {
            System.out.println((i + 1) + ". " + flowerList.get(i));
        }
        int index;
        while (true) {
            System.out.print("Виберіть номер квітки для редагування (1-" + flowerList.size() + "): ");
            index = scanner.nextInt() - 1;
            if (index >= 0 && index < flowerList.size()) {
                return flowerList.get(index);
            } else {
                logger.warning("Користувач ввів некоректний номер квітки.");
                System.out.println("Некоректний номер квітки. Спробуйте ще раз.");
            }
        }
    }
}
