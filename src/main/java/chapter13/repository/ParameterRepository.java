package chapter13.repository;

import chapter13.entity.Parameter;
import chapter13.db.DatabaseConnection;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ParameterRepository {
    public static void create(Parameter parameter) {
        String sql = "INSERT INTO Parameter (name, unit, param_group_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, parameter.getName());
            stmt.setString(2, parameter.getUnit());
            stmt.setInt(3, parameter.getParamGroupId());
            stmt.executeUpdate();
            // После создания нужно получить сгенерированный идентификатор
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    parameter.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Parameter getById(int id) throws SQLException {
        String sql = "SELECT * FROM Parameter WHERE param_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Parameter parameter = new Parameter();
                    parameter.setId(rs.getInt("param_id"));
                    parameter.setName(rs.getString("name"));
                    parameter.setUnit(rs.getString("unit"));
                    parameter.setParamGroupId(rs.getInt("param_group_id"));
                    return parameter;
                }
            }
        }
        return null;
    }

    public static List<Parameter> getParametersByProductGroupId(int id) throws SQLException {
        List<Parameter> parameters = new ArrayList<>();
        String sql = "SELECT * FROM Parameter, ParameterGroup" +
                " WHERE ParameterGroup.product_group_id = ?" +
                " AND Parameter.param_group_id = ParameterGroup.param_group_id";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs2 = stmt.executeQuery()) {
                while (rs2.next()) {
                    Parameter parameter = new Parameter();
                    parameter.setId(rs2.getInt("param_id"));
                    parameter.setName(rs2.getString("name"));
                    parameter.setUnit(rs2.getString("unit"));
                    parameter.setParamGroupId(rs2.getInt("param_group_id"));
                    parameters.add(parameter);
                }
            }
        }
        return parameters;
    }

    // Запрос на извлечение из Parameter всех параметров указанной группы
    public static List<Parameter> getParametersByParamGroupId(int id) throws SQLException {
        String sql = "SELECT * FROM Parameter WHERE param_group_id = ?";
        List<Parameter> parameters = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs2 = stmt.executeQuery()) {
                while (rs2.next()) {
                    Parameter parameter = new Parameter();
                    parameter.setId(rs2.getInt("param_id"));
                    parameter.setName(rs2.getString("name"));
                    parameter.setUnit(rs2.getString("unit"));
                    parameter.setParamGroupId(rs2.getInt("param_group_id"));
                    parameters.add(parameter);
                }
            }
        }
        return parameters;
    }

    public static List<Parameter> getAll() throws SQLException {
        List<Parameter> parameters = new ArrayList<>();
        String sql = "SELECT * FROM Parameter";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Parameter parameter = new Parameter();
                parameter.setId(rs.getInt("param_id"));
                parameter.setName(rs.getString("name"));
                parameter.setUnit(rs.getString("unit"));
                parameter.setParamGroupId(rs.getInt("param_group_id"));
                parameters.add(parameter);
            }
        }
        return parameters;
    }

    public static void update(Parameter parameter) throws SQLException {
        String sql = "UPDATE Parameter SET name = ?, unit = ?, param_group_id = ? WHERE param_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, parameter.getName());
            stmt.setString(2, parameter.getUnit());
            stmt.setInt(3, parameter.getParamGroupId());
            stmt.setInt(4, parameter.getId());
            stmt.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM Parameter WHERE param_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
