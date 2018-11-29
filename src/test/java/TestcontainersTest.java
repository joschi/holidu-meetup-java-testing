import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertTrue;

public class TestcontainersTest {
    @Rule
    public final PostgreSQLContainer pgSql = new PostgreSQLContainer()
            .withDatabaseName("holidu")
            .withUsername("holidu")
            .withPassword("secret");

    @Test
    public void testQuery() throws Exception {
        assertTrue(pgSql.isRunning());

        try (Connection db = DriverManager.getConnection(pgSql.getJdbcUrl(), pgSql.getUsername(), pgSql.getPassword());
             Statement statement = db.createStatement()) {

            ResultSet rs = statement.executeQuery("SELECT TRUE");
            while (rs.next()) {
                boolean b = rs.getBoolean(1);
                assertTrue(b);
            }
        }

    }
}
