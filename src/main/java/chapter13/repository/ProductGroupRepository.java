package chapter13.repository;

import chapter13.entity.Parameter;
import chapter13.entity.Product;
import chapter13.entity.ProductGroup;
import chapter13.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductGroupRepository {
    public static void create(ProductGroup productGroup) {
        String sql = "INSERT INTO ProductGroup (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, productGroup.getName());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    productGroup.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProductGroup getById(int id) throws SQLException {
        String sql = "SELECT * FROM ProductGroup WHERE group_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProductGroup productGroup = new ProductGroup();
                    int groupId = rs.getInt("group_id");
                    productGroup.setId(groupId);
                    productGroup.setName(rs.getString("name"));
                    // Получение продуктов данной группы
                    List<Product> products = ProductRepository.getByGroupId(groupId);
                    productGroup.setProducts(products);
                    return productGroup;
                }
            }
        }
        return null;
    }

    public static List<ProductGroup> getAll() throws SQLException {
        List<ProductGroup> productGroups = new ArrayList<>();
        String sql = "SELECT * FROM ProductGroup";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ProductGroup productGroup = new ProductGroup();
                productGroup.setId(rs.getInt("group_id"));
                productGroup.setName(rs.getString("name"));
                productGroups.add(productGroup);
            }
        }
        return productGroups;
    }

    public static void update(ProductGroup productGroup) throws SQLException {
        String sql = "UPDATE ProductGroup SET name = ? WHERE group_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productGroup.getName());
            stmt.setInt(2, productGroup.getId());
            stmt.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        // Сперва удаление всех продуктов данной группы
        List<Product> products = ProductRepository.getByProductGroup(id);
        for (Product product : products) {
            ProductRepository.delete(product.getId());
        }
        // Удаление самой группы
        String sql = "DELETE FROM ProductGroup WHERE group_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}