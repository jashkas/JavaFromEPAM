package chapter13.repository;

import chapter13.db.DatabaseConnection;
import chapter13.entity.Parameter;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Для BeforeAll

public class ParameterRepositoryTest {
    private Connection connection;

    @BeforeAll
    public void setUpDatabase() throws SQLException, ClassNotFoundException {
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
    public void testCreateParameter() throws SQLException {
        Parameter parameter = new Parameter("Тест параметр", "миллиметры", 10);
        ParameterRepository.create(parameter);

        Parameter retrivedParameter = ParameterRepository.getById(parameter.getId());

        assertNotNull(retrivedParameter);
        assertEquals("Тест параметр", retrivedParameter.getName());
        assertEquals(10, retrivedParameter.getParamGroupId());

        ParameterRepository.delete(parameter.getId());
    }

    @Test
    public void testUpdateParameter() throws SQLException {
        Parameter parameter = new Parameter("Тест параметр", "миллиметры", 10);
        ParameterRepository.create(parameter);
        Parameter retrivedParameter = ParameterRepository.getById(parameter.getId());

        assertNotNull(retrivedParameter);

        retrivedParameter.setName("Тестовый");
        retrivedParameter.setUnit("сантиметры");

        ParameterRepository.update(retrivedParameter);

        parameter = ParameterRepository.getById(parameter.getId());
        assertNotNull(parameter);
        assertEquals("Тестовый", parameter.getName());
        assertEquals("сантиметры", parameter.getUnit());

        ParameterRepository.delete(parameter.getId());

        assertNull(ParameterRepository.getById(parameter.getId()));
    }
}
