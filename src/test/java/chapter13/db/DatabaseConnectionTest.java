package chapter13.db;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Для BeforeAll

public class DatabaseConnectionTest {
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
    //
    // Далее в тестах проверяются изначальные 10 строк каждой таблицы
    //
    @Test
    public void testProductGroupTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ProductGroup");
            StringBuilder resultBuilder = new StringBuilder();
            String BLANK = ";";
            int i = 0;
            while (rs.next() && i < 10) {
                resultBuilder.append("group_id:").append(rs.getInt("group_id")).append(BLANK)
                        .append(" name:").append(rs.getString("name")).append(BLANK).append("\n");
                i++;
            }
            String expectedOutput = "group_id:1; name:Электроника;\n" +
                    "group_id:2; name:Техника;\n" +
                    "group_id:3; name:Мебель;\n" +
                    "group_id:4; name:Игрушки;\n" +
                    "group_id:5; name:Одежда;\n" +
                    "group_id:6; name:Книги;\n" +
                    "group_id:7; name:Продукты;\n" +
                    "group_id:8; name:Спортивное снаряжение;\n" +
                    "group_id:9; name:Инструменты;\n" +
                    "group_id:10; name:Автотранспорт;\n";
            assertEquals(expectedOutput, resultBuilder.toString(), "Выборка из ProductGroup неверна");
        }
    }
    @Test
    public void testParameterGroupTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ParameterGroup");
            StringBuilder resultBuilder = new StringBuilder();
            String BLANK = ";";
            int i = 0;
            while (rs.next() && i < 10) {
                resultBuilder.append("param_group_id:").append(rs.getInt("param_group_id")).append(BLANK)
                        .append(" name:").append(rs.getString("name")).append(BLANK)
                        .append(" product_group_id:").append(rs.getInt("product_group_id")).append(BLANK).append("\n");
                i++;
            }
            String expectedOutput = "param_group_id:1; name:Размеры; product_group_id:1;\n" +
                    "param_group_id:2; name:Производительность; product_group_id:1;\n" +
                    "param_group_id:3; name:Размер; product_group_id:2;\n" +
                    "param_group_id:4; name:Энергопотребление; product_group_id:2;\n" +
                    "param_group_id:5; name:Материал; product_group_id:3;\n" +
                    "param_group_id:6; name:Стандарты безопасности; product_group_id:4;\n" +
                    "param_group_id:7; name:Тип ткани; product_group_id:5;\n" +
                    "param_group_id:8; name:Автор; product_group_id:6;\n" +
                    "param_group_id:9; name:Пищевая ценность; product_group_id:7;\n" +
                    "param_group_id:10; name:Долговечность; product_group_id:8;\n";
            assertEquals(expectedOutput, resultBuilder.toString(), "Выборка из ParameterGroup неверна");
        }
    }
    @Test
    public void testParameterTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Parameter");
            StringBuilder resultBuilder = new StringBuilder();
            String BLANK = ";";
            int i = 0;

            while (rs.next() && i < 10) {
                resultBuilder.append("param_id:").append(rs.getInt("param_id")).append(BLANK)
                        .append(" name:").append(rs.getString("name")).append(BLANK)
                        .append(" unit:").append(rs.getString("unit")).append(BLANK)
                        .append(" param_group_id:").append(rs.getInt("param_group_id")).append(BLANK).append("\n");
                i++;
            }
            String expectedOutput = "param_id:1; name:Рост; unit:см; param_group_id:1;\n" +
                    "param_id:2; name:Вес; unit:кг; param_group_id:1;\n" +
                    "param_id:3; name:Частота процессора; unit:ГГц; param_group_id:2;\n" +
                    "param_id:4; name:ОЗУ; unit:ГБ; param_group_id:2;\n" +
                    "param_id:5; name:Количество потоков; unit:поток; param_group_id:2;\n" +
                    "param_id:6; name:Длина; unit:см; param_group_id:3;\n" +
                    "param_id:7; name:Энергоэффективность; unit:звёзд; param_group_id:4;\n" +
                    "param_id:8; name:Тип; unit:хлопок; param_group_id:5;\n" +
                    "param_id:9; name:Рекомендованный возраст; unit:лет; param_group_id:6;\n" +
                    "param_id:10; name:Страницы; unit:страниц; param_group_id:8;\n";
            assertEquals(expectedOutput, resultBuilder.toString(), "Выборка из Parameter неверна");
        }
    }
    @Test
    public void testProductTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
            StringBuilder resultBuilder = new StringBuilder();
            String BLANK = ";";
            int i = 0;
            while (rs.next() && i < 10) {
                resultBuilder.append("product_id:").append(rs.getInt("product_id")).append(BLANK)
                        .append(" name:").append(rs.getString("name")).append(BLANK)
                        .append(" description:").append(rs.getString("description")).append(BLANK)
                        .append(" release_date:").append(rs.getDate("release_date")).append(BLANK)
                        .append(" product_group_id:").append(rs.getInt("product_group_id")).append(BLANK).append("\n");
                i++;
            }
            String expectedOutput = "product_id:1; name:Смартфон; description:Новейшая модель смартфона с функциями искусственного интеллекта; release_date:2024-01-01; product_group_id:1;\n" +
                    "product_id:2; name:Телевизор 4K; description:Телевизор с высоким разрешением и интеллектуальными функциями; release_date:2024-02-15; product_group_id:1;\n" +
                    "product_id:3; name:Микроволновая печь; description:Энергоэффективная микроволновая печь с функцией гриля; release_date:2024-03-10; product_group_id:2;\n" +
                    "product_id:4; name:Блендер; description:Высокоскоростной блендер для смузи; release_date:2024-04-22; product_group_id:2;\n" +
                    "product_id:5; name:Диван; description:Удобный 3-местный диван; release_date:2024-05-30; product_group_id:3;\n" +
                    "product_id:6; name:Игрушечный автомобиль; description:Игрушечный автомобиль с дистанционным управлением; release_date:2024-06-18; product_group_id:4;\n" +
                    "product_id:7; name:Футболка; description:Хлопковая футболка с современным дизайном; release_date:2024-07-20; product_group_id:5;\n" +
                    "product_id:8; name:Роман; description:Бестселлер в жанре научной фантастики; release_date:2024-08-25; product_group_id:6;\n" +
                    "product_id:9; name:Органическое молоко; description:Свежее органическое молоко; release_date:2024-09-05; product_group_id:7;\n" +
                    "product_id:10; name:Футбольный мяч; description:Оригинальный размер и вес футбольного мяча; release_date:2024-10-10; product_group_id:8;\n";

            assertEquals(expectedOutput, resultBuilder.toString(), "Выборка из Product неверна");
        }
    }
    @Test
    public void testProductParameterTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM ProductParameter");
            StringBuilder resultBuilder = new StringBuilder();
            String BLANK = ";";
            int i = 0;
            while (rs.next() && i < 10) {
                resultBuilder.append("product_id:").append(rs.getInt("product_id")).append(BLANK)
                        .append(" param_id:").append(rs.getInt("param_id")).append(BLANK)
                        .append(" value_param:").append(rs.getString("value_param")).append(BLANK).append("\n");
                i++;
            }
            String expectedOutput = "product_id:1; param_id:1; value_param:14;\n" +
                    "product_id:1; param_id:2; value_param:0.150;\n" +
                    "product_id:1; param_id:3; value_param:2.5;\n" +
                    "product_id:1; param_id:4; value_param:4;\n" +
                    "product_id:2; param_id:1; value_param:93;\n" +
                    "product_id:2; param_id:2; value_param:12;\n" +
                    "product_id:3; param_id:5; value_param:29.5;\n" +
                    "product_id:3; param_id:6; value_param:A+;\n" +
                    "product_id:4; param_id:5; value_param:20;\n" +
                    "product_id:5; param_id:7; value_param:Кожа;\n";
            assertEquals(expectedOutput, resultBuilder.toString(), "Выборка из ProductParameter неверна");
        }
    }
}
