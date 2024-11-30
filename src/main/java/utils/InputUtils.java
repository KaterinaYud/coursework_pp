package utils;
import java.util.Scanner;
import java.util.logging.Logger;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerManager.getLogger();

    public static int getValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                logger.info("Отримано ціле число: " + value);
                return value;
            } else {
                String invalidInput = scanner.next();
                logger.warning("Некоректний ввід цілого числа: " + invalidInput);
                System.out.println("Неправильний формат. Введіть ціле число.");
            }
        }
    }

    public static double getValidDouble(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                double value = Double.parseDouble(input);
                logger.info("Отримано дійсне число: " + value);
                return value;
            } catch (NumberFormatException e) {
                logger.warning("Некоректний ввід дійсного числа: " + input);
                System.out.println("Некоректний ввід");
            }
        }
    }

    public static boolean getValidBoolean(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("true")) {
                logger.info("Отримано булеве значення: true");
                return true;
            } else if (input.equalsIgnoreCase("false")) {
                logger.info("Отримано булеве значення: false");
                return false;
            } else {
                logger.warning("Некоректний ввід булевого значення: " + input);
                System.out.println("Некоректний ввід");
            }
        }
    }

    public static String getStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        String input = scanner.nextLine();
        logger.info("Отримано рядок: " + input);
        return input;
    }
}
