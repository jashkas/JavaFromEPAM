package chapter13.db;

import java.sql.*;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        //Class.forName ("org.h2.Driver");

        String url = "jdbc:h2:file:./data/mydb;";
        String user = "sa";
        String password = "";

        Connection connection = DriverManager.getConnection(url, user, password);
        createTablesIfNotExist(connection);
        // Внесение данных в первый запуск
        //clearAllTables(connection);
        //insertDataToTables(connection);

        return connection;
    }

    private static void createTablesIfNotExist(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // SQL для создания таблиц, если они не существуют
            stmt.execute("CREATE TABLE IF NOT EXISTS ProductGroup ("
                    + "group_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(255) NOT NULL);");

            stmt.execute("CREATE TABLE IF NOT EXISTS ParameterGroup ("
                    + "param_group_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "product_group_id INT, "
                    + "FOREIGN KEY (product_group_id) REFERENCES ProductGroup(group_id));");

            stmt.execute("CREATE TABLE IF NOT EXISTS Parameter ("
                    + "param_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "unit VARCHAR(50) NOT NULL, "
                    + "param_group_id INT,"
                    + "FOREIGN KEY (param_group_id) REFERENCES ParameterGroup(param_group_id));");

            stmt.execute("CREATE TABLE IF NOT EXISTS Product ("
                    + "product_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "description TEXT, "
                    + "release_date DATE, "
                    + "product_group_id INT, "
                    + "FOREIGN KEY (product_group_id) REFERENCES ProductGroup(group_id));");

            stmt.execute("CREATE TABLE IF NOT EXISTS ProductParameter ("
                    + "product_id INT, "
                    + "param_id INT, "
                    + "value_param VARCHAR(255), "
                    //+ "PRIMARY KEY (product_id, param_id), "
                    + "FOREIGN KEY (product_id) REFERENCES Product(product_id), "
                    + "FOREIGN KEY (param_id) REFERENCES Parameter(param_id));");

        }
    }

    // Внесение данных для таблиц. Для первого запуска
    private static void insertDataToTables(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // Внесение данных
            stmt.execute("INSERT INTO ProductGroup (name) VALUES ('Электроника'); " +
                    "INSERT INTO ProductGroup (name) VALUES ('Техника'); " +
                    "INSERT INTO ProductGroup (name) VALUES ('Мебель'); " +
                    "INSERT INTO ProductGroup (name) VALUES ('Игрушки'); " +
                    "INSERT INTO ProductGroup (name) VALUES ('Одежда'); " +
                    "INSERT INTO ProductGroup (name) VALUES ('Книги'); " +
                    "INSERT INTO ProductGroup (name) VALUES ('Продукты'); " +
                    "INSERT INTO ProductGroup (name) VALUES ('Спортивное снаряжение'); " +
                    "INSERT INTO ProductGroup (name) VALUES ('Инструменты'); " +
                    "INSERT INTO ProductGroup (name) VALUES ('Автотранспорт');");

            stmt.execute("INSERT INTO ParameterGroup (name, product_group_id) VALUES ('Размеры', 1); " +
                    "INSERT INTO ParameterGroup (name, product_group_id) VALUES ('Производительность', 1); " +
                    "INSERT INTO ParameterGroup (name, product_group_id) VALUES ('Размер', 2); " +
                    "INSERT INTO ParameterGroup (name, product_group_id) VALUES ('Энергопотребление', 2); " +
                    "INSERT INTO ParameterGroup (name, product_group_id) VALUES ('Материал', 3); " +
                    "INSERT INTO ParameterGroup (name, product_group_id) VALUES ('Стандарты безопасности', 4); " +
                    "INSERT INTO ParameterGroup (name, product_group_id) VALUES ('Тип ткани', 5); " +
                    "INSERT INTO ParameterGroup (name, product_group_id) VALUES ('Автор', 6); " +
                    "INSERT INTO ParameterGroup (name, product_group_id) VALUES ('Пищевая ценность', 7); " +
                    "INSERT INTO ParameterGroup (name, product_group_id) VALUES ('Долговечность', 8);");

            stmt.execute("INSERT INTO Parameter (name, unit, param_group_id) VALUES ('Рост', 'см', 1); " +
                    "INSERT INTO Parameter (name, unit, param_group_id) VALUES ('Вес', 'кг', 1); " +
                    "INSERT INTO Parameter (name, unit, param_group_id) VALUES ('Частота процессора', 'ГГц', 2); " +
                    "INSERT INTO Parameter (name, unit, param_group_id) VALUES ('ОЗУ', 'ГБ', 2); " +
                    "INSERT INTO Parameter (name, unit, param_group_id) VALUES ('Количество потоков', 'поток', 2); " +
                    "INSERT INTO Parameter (name, unit, param_group_id) VALUES ('Длина', 'см', 3); " +
                    "INSERT INTO Parameter (name, unit, param_group_id) VALUES ('Энергоэффективность', 'звёзд', 4); " +
                    "INSERT INTO Parameter (name, unit, param_group_id) VALUES ('Тип', 'хлопок', 5); " +
                    "INSERT INTO Parameter (name, unit, param_group_id) VALUES ('Рекомендованный возраст', 'лет', 6); " +
                    "INSERT INTO Parameter (name, unit, param_group_id) VALUES ('Страницы', 'страниц', 8);");

            stmt.execute("INSERT INTO Product (name, description, release_date, product_group_id) VALUES ('Смартфон', 'Новейшая модель смартфона с функциями искусственного интеллекта', '2024-01-01', 1); " +
                    "INSERT INTO Product (name, description, release_date, product_group_id) VALUES ('Телевизор 4K', 'Телевизор с высоким разрешением и интеллектуальными функциями', '2024-02-15', 1); " +
                    "INSERT INTO Product (name, description, release_date, product_group_id) VALUES ('Микроволновая печь', 'Энергоэффективная микроволновая печь с функцией гриля', '2024-03-10', 2); " +
                    "INSERT INTO Product (name, description, release_date, product_group_id) VALUES ('Блендер', 'Высокоскоростной блендер для смузи', '2024-04-22', 2); " +
                    "INSERT INTO Product (name, description, release_date, product_group_id) VALUES ('Диван', 'Удобный 3-местный диван', '2024-05-30', 3); " +
                    "INSERT INTO Product (name, description, release_date, product_group_id) VALUES ('Игрушечный автомобиль', 'Игрушечный автомобиль с дистанционным управлением', '2024-06-18', 4); " +
                    "INSERT INTO Product (name, description, release_date, product_group_id) VALUES ('Футболка', 'Хлопковая футболка с современным дизайном', '2024-07-20', 5); " +
                    "INSERT INTO Product (name, description, release_date, product_group_id) VALUES ('Роман', 'Бестселлер в жанре научной фантастики', '2024-08-25', 6); " +
                    "INSERT INTO Product (name, description, release_date, product_group_id) VALUES ('Органическое молоко', 'Свежее органическое молоко', '2024-09-05', 7); " +
                    "INSERT INTO Product (name, description, release_date, product_group_id) VALUES ('Футбольный мяч', 'Оригинальный размер и вес футбольного мяча', '2024-10-10', 8);");

            stmt.execute("INSERT INTO ProductParameter (product_id, param_id, value_param) VALUES (1, 1, '14'); " +
                    "INSERT INTO ProductParameter (product_id, param_id, value_param) VALUES (1, 2, '0.150'); " +
                    "INSERT INTO ProductParameter (product_id, param_id, value_param) VALUES (1, 3, '2.5'); " +
                    "INSERT INTO ProductParameter (product_id, param_id, value_param) VALUES (1, 4, '4'); " +
                    "INSERT INTO ProductParameter (product_id, param_id, value_param) VALUES (2, 1, '93'); " +
                    "INSERT INTO ProductParameter (product_id, param_id, value_param) VALUES (2, 2, '12');  " +
                    "INSERT INTO ProductParameter (product_id, param_id, value_param) VALUES (3, 5, '29.5'); " +
                    "INSERT INTO ProductParameter (product_id, param_id, value_param) VALUES (3, 6, 'A+'); " +
                    "INSERT INTO ProductParameter (product_id, param_id, value_param) VALUES (4, 5, '20'); " +
                    "INSERT INTO ProductParameter (product_id, param_id, value_param) VALUES (5, 7, 'Кожа');");

        }
    }
    private static void clearAllTables(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM ProductParameter;");
            stmt.execute("DELETE FROM Product;");
            stmt.execute("DELETE FROM Parameter;");
            stmt.execute("DELETE FROM ParameterGroup;");
            stmt.execute("DELETE FROM ProductGroup;");
        }
    }
}
