package database;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

class FlowerDatabaseSetupTest {
    private static final String TEST_DB = "flowers_test.db";
    private static final String TEST_DB_URL = "jdbc:sqlite:" + TEST_DB;
    private static Connection connection;

    @BeforeAll
    static void setUpAll() throws Exception {
        connection = DriverManager.getConnection(TEST_DB_URL);
    }

    @AfterAll
    static void tearDownAll() throws Exception {
        if (connection != null) connection.close();
        Files.deleteIfExists(Paths.get(TEST_DB));
    }

    @BeforeEach
    void setUp() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS flowers");
        }
    }

    @Test
    void testMainExecutesWithoutErrors() {
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        String originalDb = "flowers.db";
        try {
            Files.deleteIfExists(Paths.get(originalDb));
            FlowerDatabaseSetup.main(new String[]{});
            assertTrue(new File(originalDb).exists(), "БД повинна бути створена");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + originalDb);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM flowers")) {
                assertTrue(rs.next());
                int count = rs.getInt(1);
                assertEquals(6, count, "У базі повинно бути 6 квітів");
            }
        } catch (Exception e) {
            fail("main() не повинен викидати винятки: " + e.getMessage());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testInsertFlowerWithAllFields() throws SQLException {
        createFlowersTable();
        FlowerDatabaseSetup.insertFlower(connection,
                "Троянда", "Rose", "білий", 10.5, 2, 20.0,
                true, 12, null, "рожевий", 5);
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM flowers WHERE name='Троянда'")) {
            assertTrue(rs.next()); // Тепер має бути true
            assertEquals("Rose", rs.getString("type"));
            assertEquals("рожевий", rs.getString("pattern_color"));
            assertEquals(5, rs.getInt("smell_intensity"));
        }
    }

    @Test
    void testInsertFlowerWithNullOptionalFields() throws SQLException {
        createFlowersTable();
        FlowerDatabaseSetup.insertFlower(connection,
                "Без запаху", "TypeX", "жовтий", 5.0, 1, 15.0,
                null, null, null, null, null);

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM flowers WHERE name='Без запаху'")) {
            assertTrue(rs.next());
            assertNull(rs.getObject("has_spikes"));
            assertNull(rs.getObject("petal_count"));
            assertNull(rs.getObject("pattern_color"));
        }
    }

    private void createFlowersTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS flowers (" +
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
                    ");");
        }
    }
}
