package chapter13.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        //Class.forName ("org.h2.Driver");

        String url = "jdbc:h2:file:./data/testdb";
        String user = "sa";
        String password = ""; // пароль по умолчанию

        Connection connection = DriverManager.getConnection(url, user, password);
        createTablesIfNotExist(connection);
        return connection;
    }

    private static void createTablesIfNotExist(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // SQL для создания таблиц, если они не существуют
            stmt.execute("CREATE TABLE IF NOT EXISTS Parameter ("
                    + "param_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "unit VARCHAR(50) NOT NULL, "
                    + "param_group_id INT, "
                    + "FOREIGN KEY (param_group_id) REFERENCES ParameterGroup(param_group_id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS Product ("
                    + "product_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "description TEXT, "
                    + "release_date DATE, "
                    + "product_group_id INT, "
                    + "FOREIGN KEY (product_group_id) REFERENCES ProductGroup(group_id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS ProductGroup ("
                    + "group_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(255) NOT NULL)");

            stmt.execute("CREATE TABLE IF NOT EXISTS ParameterGroup ("
                    + "param_group_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "product_group_id INT, "
                    + "FOREIGN KEY (product_group_id) REFERENCES ProductGroup(group_id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS ProductParameter ("
                    + "product_id INT, "
                    + "param_id INT, "
                    + "value VARCHAR(255), "
                    + "PRIMARY KEY (product_id, param_id), "
                    + "FOREIGN KEY (product_id) REFERENCES Product(product_id), "
                    + "FOREIGN KEY (param_id) REFERENCES Parameter(param_id))");
        }
    }
}
