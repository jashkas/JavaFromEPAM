package chapter13.repository;

import chapter13.entity.Parameter;
import chapter13.entity.ParameterGroup;
import chapter13.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParameterGroupRepository {
    public static void create(ParameterGroup parameterGroup) {
        String sql = "INSERT INTO ParameterGroup (name, product_group_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, parameterGroup.getName());
            stmt.setInt(2, parameterGroup.getProductGroupId());
            stmt.executeUpdate();
            // Присвоение сгенерированного ключа
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    parameterGroup.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            for (Parameter parameter : parameterGroup.getParameters()) {
                // Присвоение каждому параметру конкретной переданной группы parameterGroup
                parameter.setParamGroupId(parameterGroup.getId());
                // Если параметр в списке существует в базе данных, то обновление
                if (ParameterRepository.getById(parameter.getId()) != null) {
                    ParameterRepository.update(parameter);
                } else {
                    // если переданные параметры не существуют в базе, то создание их
                    ParameterRepository.create(parameter);
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ParameterGroup getById(int id) throws SQLException {
        ParameterGroup parameterGroup = null;
        String sql = "SELECT * FROM ParameterGroup WHERE param_group_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Выполнение первого запроса на извлечение из ParameterGroup
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    parameterGroup = new ParameterGroup();
                    parameterGroup.setId(rs.getInt("param_group_id"));
                    parameterGroup.setName(rs.getString("name"));
                    parameterGroup.setProductGroupId(rs.getInt("product_group_id"));
                    parameterGroup.setParameters(ParameterRepository.getParametersByParamGroupId(id));
                }
            }
        }
        return parameterGroup;
    }

    public static List<ParameterGroup> getAll() throws SQLException {
        List<ParameterGroup> parameterGroups = new ArrayList<>();
        String sql = "SELECT * FROM ParameterGroup";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ParameterGroup parameterGroup = new ParameterGroup();
                int id = rs.getInt("param_group_id");
                parameterGroup.setId(id);
                parameterGroup.setName(rs.getString("name"));
                parameterGroup.setProductGroupId(rs.getInt("product_group_id"));
                parameterGroup.setParameters(ParameterRepository.getParametersByParamGroupId(id));
                parameterGroups.add(parameterGroup);
            }
        }
        return parameterGroups;
    }

    public static void update(ParameterGroup parameterGroup) throws SQLException {
        String sql = "UPDATE ParameterGroup SET name = ?, product_group_id = ? WHERE param_group_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, parameterGroup.getName());
            stmt.setInt(2, parameterGroup.getProductGroupId());
            stmt.setInt(3, parameterGroup.getId());
            stmt.executeUpdate();
        }
    }

    public static void updateProductGroup(List<ParameterGroup> parameterGroups, int productGroupId) throws SQLException {
        // Запрос
        String sql = "UPDATE ParameterGroup SET product_group_id = ? WHERE param_group_id IN (" +
                "?, ".repeat(parameterGroups.size() - 1) + "?" +
                ");";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productGroupId);
            // ind - индекс параметра sql
            for (int i = 0, ind = 2; i < parameterGroups.size(); i++, ind++) {
                stmt.setInt(ind, parameterGroups.get(i).getId());
            }
            stmt.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        // Удаление всех параметров данной группы
        List<Parameter> parameters = ParameterRepository.getParametersByParamGroupId(id);
        for (Parameter parameter : parameters) {
            ParameterRepository.delete(parameter.getId());
        }
        // Удаление группы параметров
        String sql = "DELETE FROM ParameterGroup WHERE param_group_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static void show(List<ParameterGroup> parameterGroups) {
        for (ParameterGroup parameterGroup : parameterGroups) {
            System.out.println(parameterGroup);
        }
    }
}