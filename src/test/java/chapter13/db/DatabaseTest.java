package chapter13.db;

import chapter13.entity.Parameter;
import chapter13.repository.ParameterRepository;
import chapter8.VariableB.Paragraph;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Для BeforeAll

public class DatabaseTest {
    private Connection connection;

    @BeforeAll
    public void setUp() throws Exception {
        Class.forName("org.h2.Driver");
        connection = DatabaseConnection.getConnection();
    }
    @AfterAll
    public void tearDown() throws Exception {
        // Закрываем соединение
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
    @Test
    public void testConnection() throws Exception {
        assertNotNull(connection);
        assertFalse(connection.isClosed(), "Connection должно быть открыто");
    }
    @Test
    public void testProductGroupTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ProductGroup");
            while (rs.next()) {
                String result = "group_id:" + rs.getInt("group_id")
                        + " name:" + rs.getString("name");
                System.out.println(result);
            }
        }
    }
    @Test
    public void testParameterGroupTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ParameterGroup");
            while (rs.next()) {
                String result = "param_group_id:" + rs.getInt("param_group_id")
                        + " name:" + rs.getString("name")
                        + " product_group_id:" + rs.getInt("product_group_id");
                System.out.println(result);
            }
        }
    }
    @Test
    public void testParameterTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            //stmt.execute("DELETE FROM Parameter WHERE param_id = 51");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Parameter");
            while (rs.next()) {
                String result = "param_id:" + rs.getInt("param_id")
                        + " name:" + rs.getString("name")
                        + " unit:" + rs.getString("unit")
                        + " param_group_id:" + rs.getInt("param_group_id");
                System.out.println(result);
            }
        }
    }
    @Test
    public void testProductTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
            while (rs.next()) {
                String result = "product_id:" + rs.getInt("product_id")
                        + " name:" + rs.getString("name")
                        + " description:" + rs.getString("description")
                        + " release_date:" + rs.getDate("release_date")
                        + " product_group_id:" + rs.getInt("product_group_id");
                System.out.println(result);
            }
        }
    }
    @Test
    public void testProductParameterTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ProductParameter");
            while (rs.next()) {
                String result = "product_id:" + rs.getInt("product_id")
                        + " param_id:" + rs.getString("param_id")
                        + " value_param:" + rs.getString("value_param");
                System.out.println(result);
            }
        }
    }

}
