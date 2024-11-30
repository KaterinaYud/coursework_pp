package utils;

import flower.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReadFromFile {
    private static final Logger logger = LoggerManager.getLogger();

    public static List<Flower> flowersFromFile(String filename) {
        List<Flower> flowers = new ArrayList<>();
        if (!Files.exists(Paths.get(filename))) {
            logger.warning("Файл не знайдено: " + filename);
            System.out.println("Файл не знайдено: " + filename);
            return flowers;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    String type = parts[0].trim();
                    String color = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    int freshness = Integer.parseInt(parts[3].trim());
                    int stemLength = Integer.parseInt(parts[4].trim());

                    // Заміна на традиційний switch
                    switch (type) {
                        case "Rose":
                            boolean hasThorns = Boolean.parseBoolean(parts[5].trim());
                            flowers.add(new Rose(color, price, freshness, stemLength, hasThorns));
                            break;
                        case "Lily":
                            boolean hasFragrance = Boolean.parseBoolean(parts[5].trim());
                            flowers.add(new Lily(color, price, freshness, stemLength, hasFragrance));
                            break;
                        case "Chamomile":
                            int petalsCount = Integer.parseInt(parts[5].trim());
                            flowers.add(new Chamomile(color, price, freshness, stemLength, petalsCount));
                            break;
                        default:
                            logger.warning("Невідомий тип квітки: " + type);
                            System.out.println("Невідомий тип квітки: " + type);
                            break;
                    }
                } catch (Exception e) {
                    logger.warning("Помилка при обробці рядка: " + line + ". Помилка: " + e.getMessage());
                    System.out.println("Помилка при обробці рядка: " + line);
                }
            }
            logger.info("Файл успішно оброблено: " + filename);
        } catch (IOException e) {
            logger.severe("Помилка при читанні файлу: " + filename + ". Помилка: " + e.getMessage());
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
        return flowers;
    }
}
