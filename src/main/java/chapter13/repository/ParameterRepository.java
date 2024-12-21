package chapter13.repository;

import chapter13.entity.Parameter;
import chapter13.db.DatabaseConnection;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ParameterRepository {
    public void create(Parameter parameter) {
        String sql = "INSERT INTO Parameter (name, unit, paramGroupId) VALUES (?, ?, ?)";
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

    public Parameter getById(int id) throws SQLException {
        String sql = "";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Parameter parameter = new Parameter();
                    parameter.setId(rs.getInt("id"));
                    parameter.setName(rs.getString("name"));
                    parameter.setUnit(rs.getString("unit"));
                    parameter.setParamGroupId(rs.getInt("paramGroupId"));
                    return parameter;
                }
            }
        }
        return null;
    }

    public List<Parameter> getAll() throws SQLException {
        List<Parameter> parameters = new ArrayList<>();
        String sql = "SELECT * FROM Parameter";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Parameter parameter = new Parameter();
                parameter.setId(rs.getInt("id"));
                parameter.setName(rs.getString("name"));
                parameter.setUnit(rs.getString("unit"));
                parameter.setParamGroupId(rs.getInt("paramGroupId"));
                parameters.add(parameter);
            }
        }
        return parameters;
    }

    public void update(Parameter carModel) throws SQLException {
        String sql = "UPDATE Parameter SET name = ?, unit = ?, paramGroupId = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carModel.getName());
            stmt.setString(2, carModel.getUnit());
            stmt.setInt(4, carModel.getParamGroupId());
            stmt.setInt(4, carModel.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Parameter WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
