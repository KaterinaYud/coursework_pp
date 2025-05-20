package database;
import flower.*;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import java.sql.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlowerRepositoryTest {
    private FlowerRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FlowerRepository();
    }

    @Test
    void testLoadFlowers_withUnknownType() throws SQLException {
        Connection conn = mock(Connection.class);
        Statement stmt = mock(Statement.class);
        ResultSet rs = mock(ResultSet.class);
        try (MockedStatic<DriverManager> dm = mockStatic(DriverManager.class)) {
            dm.when(() -> DriverManager.getConnection(anyString())).thenReturn(conn);
            when(conn.createStatement()).thenReturn(stmt);
            when(stmt.executeQuery(anyString())).thenReturn(rs);
            when(rs.next()).thenReturn(true, false);
            when(rs.getString("name")).thenReturn("UnknownFlower");
            when(rs.getString("type")).thenReturn("UnknownType");
            when(rs.getString("color")).thenReturn("White");
            when(rs.getDouble("price")).thenReturn(5.0);
            when(rs.getInt("freshness")).thenReturn(2);
            when(rs.getDouble("stem_length")).thenReturn(15.0);
            List<Flower> result = repository.loadFlowers();
            assertEquals(0, result.size());
        }
    }

    @Test
    void testLoadFlowers_withEmptyResultSet() throws SQLException {
        Connection conn = mock(Connection.class);
        Statement stmt = mock(Statement.class);
        ResultSet rs = mock(ResultSet.class);
        try (MockedStatic<DriverManager> dm = mockStatic(DriverManager.class)) {
            dm.when(() -> DriverManager.getConnection(anyString())).thenReturn(conn);
            when(conn.createStatement()).thenReturn(stmt);
            when(stmt.executeQuery(anyString())).thenReturn(rs);
            when(rs.next()).thenReturn(false);
            List<Flower> result = repository.loadFlowers();
            assertTrue(result.isEmpty());
        }
    }
}
