package chapter13.repository;

import chapter13.db.DatabaseConnection;
import chapter13.entity.Parameter;
import chapter13.entity.ParameterGroup;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Для BeforeAll

public class ParameterGroupRepositoryTest {
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
        List<Parameter> list = List.of(new Parameter[]{
                new Parameter("Тестовый параметр", "единица"),
                new Parameter("второй тестовый параметр", "тест единица измерения")
        });

        ParameterGroup parameterGroup = new ParameterGroup("Первая тестовая группа параметров", 10, list);
        ParameterGroupRepository.create(parameterGroup);

        ParameterGroup retrivedParameterGroup = ParameterGroupRepository.getById(parameterGroup.getId());

        assertNotNull(retrivedParameterGroup);
        assertEquals("Первая тестовая группа параметров", retrivedParameterGroup.getName());
        assertEquals(10, retrivedParameterGroup.getProductGroupId());
        // Список параметров должен содержать id присвоенные базой данных
        List<Parameter> retrivedParameters = retrivedParameterGroup.getParameters();
        // Удаление группы
        ParameterGroupRepository.delete(retrivedParameterGroup.getId());
        assertNull(ParameterGroupRepository.getById(retrivedParameterGroup.getId()));
        // Проверка, что связанные параметры удалились с группой
        for (Parameter param: retrivedParameters) {
            assertNull(ParameterRepository.getById(param.getId()));
        }
    }

    @Test
    public void testUpdateParameter() throws SQLException {
        List<Parameter> list = List.of(new Parameter[]{
                new Parameter("Тестовый параметр", "единица"),
                new Parameter("второй тестовый параметр", "тест единица измерения")
        });

        ParameterGroup parameterGroup = new ParameterGroup("Вторая тестовая группа параметров", 10, list);
        // Создание записи в базе данных
        ParameterGroupRepository.create(parameterGroup);

        // Извлечение по id
        ParameterGroup retrivedParameterGroup = ParameterGroupRepository.getById(parameterGroup.getId());
        // Редактирование
        retrivedParameterGroup.setName("Тестовый");
        retrivedParameterGroup.setProductGroupId(1);
        // Обновление
        ParameterGroupRepository.update(retrivedParameterGroup);

        // Извлечение записи по id
        parameterGroup = ParameterGroupRepository.getById(parameterGroup.getId());
        assertEquals("Тестовый", parameterGroup.getName());
        assertEquals(1, parameterGroup.getProductGroupId());

        // Удаление тестовой записи
        ParameterGroupRepository.delete(parameterGroup.getId());

        assertNull(ParameterGroupRepository.getById(parameterGroup.getId()));
    }
}
