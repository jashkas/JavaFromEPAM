package chapter13;

import chapter13.db.*;
import chapter13.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        connection = DatabaseConnection.getConnection();
    }

    @Test
    public void testProductCreation() {
        Product product = new Product("Телефон", "Смартфон", LocalDate.now(), 1);

        assertEquals("Телефон", product.getName());
        assertEquals("Смартфон", product.getDescription());
        assertNotNull(product.getReleaseDate());
        assertEquals(1, product.getProductGroupId());
    }

    @Test
    public void testProductSetName() {
        Product product = new Product("Телефон", "Смартфон", LocalDate.now(), 1);
        product.setName("Ноутбук");

        assertEquals("Ноутбук", product.getName());
    }

    @Test
    public void testProductSettersAndGetters() {
        Product product = new Product("Телефон", "Смартфон", LocalDate.now(), 1);
        product.setDescription("Новое описание");
        product.setReleaseDate(LocalDate.of(2020, 1, 1));

        assertEquals("Новое описание", product.getDescription());
        assertEquals(LocalDate.of(2020, 1, 1), product.getReleaseDate());
    }
}
