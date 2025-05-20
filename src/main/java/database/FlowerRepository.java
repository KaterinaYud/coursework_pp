package database;
import flower.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlowerRepository {
    private static final Logger logger = Logger.getLogger(FlowerRepository.class.getName());
    private final String url = "jdbc:sqlite:flowers.db";
    public List<Flower> loadFlowers() {
        List<Flower> flowers = new ArrayList<>();
        logger.info("Розпочато завантаження квіток з бази даних.");
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM flowers")) {
            logger.info("Підключення до бази даних успішне.");
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                String color = rs.getString("color");
                double price = rs.getDouble("price");
                int freshness = rs.getInt("freshness");
                int stemLength = (int) rs.getDouble("stem_length");
                logger.info("Обробка квітки: " + name + " (" + type + ")");
                switch (type) {
                    case "Rose":
                        boolean hasSpikes = rs.getBoolean("has_spikes");
                        flowers.add(new Rose(name, type, color, price, freshness, stemLength, hasSpikes));
                        logger.info("Додано троянду: " + name);
                        break;
                    case "Chamomile":
                        int petals = rs.getInt("petal_count");
                        flowers.add(new Chamomile(name, type, color, price, freshness, stemLength, petals));
                        logger.info("Додано ромашку: " + name);
                        break;
                    case "Lily":
                        boolean isHasAroma = rs.getBoolean("has_aroma");
                        flowers.add(new Lily(name, type, color, price, freshness, stemLength, isHasAroma));
                        logger.info("Додано лілію: " + name);
                        break;
                    case "Peony":
                        boolean hasAroma = rs.getBoolean("has_aroma");
                        flowers.add(new Peony(name, type, color, price, freshness, stemLength, hasAroma));
                        logger.info("Додано півонію: " + name);
                        break;
                    case "Tulip":
                        String colorPattern = rs.getString("pattern_color");
                        flowers.add(new Tulip(name, type, color, price, freshness, stemLength, colorPattern));
                        logger.info("Додано тюльпан: " + name);
                        break;
                    case "Lisianthus":
                        int scentIntensity = rs.getInt("smell_intensity");
                        flowers.add(new Lisianthus(name, type, color, price, freshness, stemLength, scentIntensity));
                        logger.info("Додано еустому: " + name);
                        break;
                    default:
                        logger.warning("Не вдалося додати квітку через невідомий тип: " + type);
                        break;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Помилка при завантаженні квіток з бази даних", e);
        }
        logger.info("Завершено завантаження квіток.");
        return flowers;
    }
}
