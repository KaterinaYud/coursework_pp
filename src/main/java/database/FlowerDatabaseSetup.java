package database;
import java.io.File;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlowerDatabaseSetup {
    private static final Logger logger = Logger.getLogger(FlowerDatabaseSetup.class.getName());

    public static void main(String[] args) {
        String url = "jdbc:sqlite:flowers.db";
        File dbFile = new File("flowers.db");
        logger.info("Шлях до бази: " + dbFile.getAbsolutePath());
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            String createTable = "CREATE TABLE IF NOT EXISTS flowers (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "type TEXT NOT NULL," +
                    "color TEXT," +
                    "price REAL," +
                    "freshness INTEGER," +
                    "stem_length REAL," +
                    "has_spikes BOOLEAN," +
                    "petal_count INTEGER," +
                    "has_aroma BOOLEAN," +
                    "pattern_color TEXT," +
                    "smell_intensity INTEGER" +
                    ");";

            logger.info("Створення таблиці...");
            stmt.execute(createTable);
            logger.info("Таблиця створена або вже існує.");

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM flowers");
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    logger.warning("Квіти вже існують у базі. Очищаємо таблицю...");
                    stmt.executeUpdate("DELETE FROM flowers");
                    logger.info("Таблиця очищена.");
                }
            }

            insertFlower(conn, "Троянда", "Rose", "червоний", 35.0, 2, 40, true, null, null, null, null);
            insertFlower(conn, "Ромашка", "Chamomile", "білий", 18.0, 5, 20, null, 34, null, null, null);
            insertFlower(conn, "Лілія ніжна", "Lily", "рожевий", 40.0, 1, 30, null, null, true, null, null);
            insertFlower(conn, "Піон весняний", "Peony", "рожевий", 55.0, 2, 38, null, null, true, null, null);
            insertFlower(conn, "Тюльпан з візерунком", "Tulip", "жовтий", 30.0, 1, 32, null, null, null, "червоний", null);
            insertFlower(conn, "Еустома духмяна", "Lisianthus", "фіолетовий", 28.0, 3, 25, null, null, null, null, 7);
            logger.info("Квіти успішно додано до бази даних.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Помилка під час підключення або роботи з базою даних", e);
        }
    }

    static void insertFlower(Connection conn, String name, String type, String color, double price, int freshness,
                                     double stemLength, Boolean hasSpikes, Integer petalCount, Boolean isHasAroma,
                                     String patternColor, Integer smellIntensity) throws SQLException {

        String sql = "INSERT INTO flowers (name, type, color, price, freshness, stem_length, has_spikes, petal_count, has_aroma, pattern_color, smell_intensity) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, type);
            pstmt.setString(3, color);
            pstmt.setDouble(4, price);
            pstmt.setInt(5, freshness);
            pstmt.setDouble(6, stemLength);
            pstmt.setObject(7, hasSpikes);
            pstmt.setObject(8, petalCount);
            pstmt.setObject(9, isHasAroma);
            pstmt.setString(10, patternColor);
            pstmt.setObject(11, smellIntensity);
            pstmt.executeUpdate();
            logger.info("Квітка додана: " + name + " (" + type + ")");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Помилка при додаванні квітки: " + name, e);
        }
    }
}
