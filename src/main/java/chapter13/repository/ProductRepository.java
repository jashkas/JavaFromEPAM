package chapter13.repository;

import chapter13.entity.Parameter;
import chapter13.entity.Product;
import chapter13.db.DatabaseConnection;
import chapter13.entity.ProductParameter;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductRepository {
    public static void create(Product product) {
        String sql = "INSERT INTO Product (name, description, release_date, product_group_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDate(3, Date.valueOf(product.getReleaseDate()));
            stmt.setInt(4, product.getProductGroupId());
            stmt.executeUpdate();
            // После создания получить сгенерированный идентификатор
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Product> get(String sql, int id) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (id != -1) {
                stmt.setInt(1, id);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    int productId = rs.getInt("product_id");
                    product.setId(productId);
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setReleaseDate(rs.getDate("release_date").toLocalDate());
                    product.setProductGroupId(rs.getInt("product_group_id"));
                    // Получение списка параметров данной продукции
                    List<ProductParameter> parameters = ProductParameterRepository.getById(productId);
                    product.setParameters(parameters);
                    products.add(product);
                }
            }
        }
        return products;
    }

    public static Product getById(int id) throws SQLException {
        String sql = "SELECT * FROM Product WHERE product_id = ?";
        return get(sql, id).get(0);
    }

    public static List<Product> getByGroupId(int groupId) throws SQLException {
        String sql = "SELECT * FROM Product WHERE product_group_id = ?";
        return get(sql, groupId);
    }

    public static List<Product> getWithoutParameter(int parameterId) throws SQLException {
        // Продукция не содержащая заданного параметра.
        String sql = "SELECT DISTINCT pr.product_id, pr.name, pr.description, pr.release_date, pr.product_group_id " +
                "FROM Product pr " +
                "LEFT JOIN ProductParameter pp ON pr.product_id = pp.product_id " +
                "WHERE pp.param_id <> ? OR pp.param_id IS NULL";
        return get(sql, parameterId);
    }

    public static List<Product> getByProductGroup(int productGroup) throws SQLException {
        String sql = "SELECT p.product_id, p.name, p.description, p.release_date, p.product_group_id" +
                " FROM Product p" +
                " WHERE p.product_group_id = ?;";
        return get(sql, productGroup);
    }

    public static List<Product> getAll() throws SQLException {
        String sql = "SELECT * FROM Product";
        return get(sql, -1);
    }

    public static void update(Product product) throws SQLException {
        String sql = "UPDATE Product SET name = ?, description = ?, release_date = ?, product_group_id = ? WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDate(3, Date.valueOf(product.getReleaseDate()));
            stmt.setInt(4, product.getProductGroupId());
            stmt.setInt(5, product.getId());
            stmt.executeUpdate();
        }
    }

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM Product WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static void deleteByParameters(List<Parameter> parameters) throws SQLException {
//        String deleteProductParameterSQL = "DELETE FROM ProductParameter WHERE product_id IN ( SELECT product_id FROM ProductParameter WHERE param_id = ? ); ";
//        String deleteProductSQL = "DELETE FROM Product WHERE product_id IN ( SELECT product_id FROM ProductParameter WHERE param_id = ? ); ";
        Set<Integer> productIds = new HashSet<>();  // Идентификаторы продуктов

        // Собираем все product_id для указанных параметров
        String selectProductIdsSQL = "SELECT product_id FROM ProductParameter WHERE param_id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            for (Parameter parameter : parameters) {
                try (PreparedStatement stmt = conn.prepareStatement(selectProductIdsSQL)) {
                    stmt.setInt(1, parameter.getId());
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        productIds.add(rs.getInt("product_id"));
                    }
                }
            }

            // Удаляем связи в ProductParameter
            if (!productIds.isEmpty()) {
                String deleteProductParameterSQL = "DELETE FROM ProductParameter WHERE product_id IN (" +
                        "?, ".repeat(productIds.size() - 1) + "?" +
                        ")";

                try (PreparedStatement stmt = conn.prepareStatement(deleteProductParameterSQL)) {
                    int index = 1;
                    for (Integer id : productIds) {
                        stmt.setInt(index++, id);
                    }
                    stmt.executeUpdate();
                }

                // Удаляем продукты в Product
                String deleteProductSQL = "DELETE FROM Product WHERE product_id IN ("+
                        "?, ".repeat(productIds.size() - 1) + "?" +
                        ")";

                try (PreparedStatement stmt2 = conn.prepareStatement(deleteProductSQL)) {
                    int index = 1;
                    for (Integer id : productIds) {
                        stmt2.setInt(index++, id);
                    }
                    stmt2.executeUpdate();
                }
            }
        }
    }

    public static void show(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}