package chapter13.repository;

import chapter13.entity.Parameter;
import chapter13.entity.ParameterGroup;
import chapter13.entity.ProductParameter;
import chapter13.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductParameterRepository {
    public static void create(ProductParameter productParameter) {
        String sql = "INSERT INTO ProductParameter (product_id, param_id, value_param) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productParameter.getProductId());
            stmt.setInt(2, productParameter.getParamId());
            stmt.setString(3, productParameter.getValueParam());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ProductParameter> getById(int productId) throws SQLException {
        List<ProductParameter> productParameters = new ArrayList<>();
        String sql = "SELECT * FROM ProductParameter WHERE product_id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ProductParameter productParameter = new ProductParameter();
                    productParameter.setProductId(rs.getInt("product_id"));
                    int paramId = rs.getInt("param_id");
                    productParameter.setParamId(paramId);
                    productParameter.setValueParam(rs.getString("value_param"));
                    Parameter parameter = ParameterRepository.getById(paramId);
                    if (parameter != null) {
                        productParameter.setNameParam(parameter.getName());
                        productParameter.setUnitParam(parameter.getUnit());
                    }
                    productParameters.add(productParameter);
                }
            }
        }
        return productParameters;
    }

    public static ProductParameter getById(int productId, int paramId) throws SQLException {
        String sql = "SELECT * FROM ProductParameter WHERE product_id = ? AND param_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, paramId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProductParameter productParameter = new ProductParameter();
                    productParameter.setProductId(rs.getInt("product_id"));
                    int param = rs.getInt("param_id");
                    productParameter.setParamId(param);
                    productParameter.setValueParam(rs.getString("value_param"));
                    Parameter parameter = ParameterRepository.getById(param);
                    if (parameter != null) {
                        productParameter.setNameParam(parameter.getName());
                        productParameter.setUnitParam(parameter.getUnit());
                    }
                    return productParameter;
                }
            }   
        }
        return null;
    }

    public static List<ProductParameter> getAll() throws SQLException {
        List<ProductParameter> productParameters = new ArrayList<>();
        String sql = "SELECT * FROM ProductParameter";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ProductParameter productParameter = new ProductParameter();
                productParameter.setProductId(rs.getInt("product_id"));
                int paramId = rs.getInt("param_id");
                productParameter.setParamId(paramId);
                productParameter.setValueParam(rs.getString("value_param"));
                Parameter parameter = ParameterRepository.getById(paramId);
                if (parameter != null) {
                    productParameter.setNameParam(parameter.getName());
                    productParameter.setUnitParam(parameter.getUnit());
                }
                productParameters.add(productParameter);
            }
        }
        return productParameters;
    }

    public static void update(ProductParameter productParameter) throws SQLException {
        String sql = "UPDATE ProductParameter SET value_param = ? WHERE product_id = ? AND param_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productParameter.getValueParam());
            stmt.setInt(2, productParameter.getProductId());
            stmt.setInt(3, productParameter.getParamId());
            stmt.executeUpdate();
        }
    }

    public static void delete(int productId, int paramId) throws SQLException {
        String sql = "DELETE FROM ProductParameter WHERE product_id = ? AND param_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, paramId);
            stmt.executeUpdate();
        }
    }
}