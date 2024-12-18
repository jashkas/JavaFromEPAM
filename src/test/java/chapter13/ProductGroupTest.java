package chapter13;

import chapter13.db.*;
import chapter13.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductGroupTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        connection = DatabaseConnection.getConnection();
    }

    @Test
    public void testProductGroupCreation() {
        List<Product> products = new ArrayList<>();
        ProductGroup group = new ProductGroup("Электроника", products);

        assertEquals("Электроника", group.getName());
        assertEquals(products, group.getProducts());
    }

    @Test
    public void testProductGroupSetters() {
        List<Product> products = new ArrayList<>();
        ProductGroup group = new ProductGroup("Компьютеры", products);
        group.setName("Гаджеты");

        assertEquals("Гаджеты", group.getName());
    }

    @Test
    public void testProductGroupAddProduct() {
        List<Product> products = new ArrayList<>();
        ProductGroup group = new ProductGroup("Электроника", products);

        Product product = new Product("Телевизор", "4K TV", LocalDate.now(), 1);
        group.getProducts().add(product);

        assertEquals(1, group.getProducts().size());
        assertEquals("Телевизор", group.getProducts().get(0).getName());
    }
}