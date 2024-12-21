package chapter13;

import chapter13.db.DatabaseConnection;
import org.junit.jupiter.api.*;
import chapter13.entity.Parameter;
import chapter13.repository.ParameterRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Для BeforeAll

public class ParameterRepositoryTest {
    private ParameterRepository repository;

    @BeforeAll
    public void setUpDatabase() throws SQLException {
        repository = new ParameterRepository();

        try (Connection connection = DatabaseConnection.getConnection(); Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Parameter (" +
                    "    param_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "    name VARCHAR(255) NOT NULL," +
                    "    unit VARCHAR(50) NOT NULL," +
                    "    param_group_id INT," +
                    "    FOREIGN KEY (param_group_id) REFERENCES ParameterGroup(param_group_id));");
        }
    }
}
