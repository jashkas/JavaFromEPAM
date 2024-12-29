package chapter13.repository;

import chapter13.entity.Product;
import chapter13.db.DatabaseConnection;
import chapter13.entity.ProductParameter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
//        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    Product product = new Product();
//                    int productId = rs.getInt("product_id");
//                    product.setId(productId);
//                    product.setName(rs.getString("name"));
//                    product.setDescription(rs.getString("description"));
//                    product.setReleaseDate(rs.getDate("release_date").toLocalDate());
//                    product.setProductGroupId(rs.getInt("product_group_id"));
//                    // Получение списка параметров данной продукции
//                    List<ProductParameter> parameters = ProductParameterRepository.getById(productId);
//                    product.setParameters(parameters);
//                    return product;
//                }
//            }
//        }
        return get(sql, id).get(0);
    }

    public static List<Product> getByGroupId(int groupId) throws SQLException {
        String sql = "SELECT * FROM Product WHERE product_group_id = ?";

//        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, groupId);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    Product product = new Product();
//                    int productId = rs.getInt("product_id");
//                    product.setId(productId);
//                    product.setName(rs.getString("name"));
//                    product.setDescription(rs.getString("description"));
//                    product.setReleaseDate(rs.getDate("release_date").toLocalDate());
//                    product.setProductGroupId(rs.getInt("product_group_id"));
//                    // Получение списка параметров данной продукции
//                    List<ProductParameter> parameters = ProductParameterRepository.getById(productId);
//                    product.setParameters(parameters);
//                    products.add(product);
//                }
//            }
//        }
        return get(sql, groupId);
    }

    public static List<Product> getWithoutParameter(int parameterId) throws SQLException {
        String sql = "SELECT DISTINCT pr.product_id, pr.name, pr.description, pr.release_date, pr.product_group_id " +
                "FROM Product pr " +
                "LEFT JOIN ProductParameter pp ON pr.product_id = pp.product_id " +
                "WHERE pp.param_id <> ? OR pp.param_id IS NULL";
        return get(sql, parameterId);
    }

    public static List<Product> getAll() throws SQLException {
//        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
//
//        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                Product product = new Product();
//                int productId = rs.getInt("product_id");
//                product.setId(productId);
//                product.setId(rs.getInt("product_id"));
//                product.setName(rs.getString("name"));
//                product.setDescription(rs.getString("description"));
//                product.setReleaseDate(rs.getDate("release_date").toLocalDate());
//                product.setProductGroupId(rs.getInt("product_group_id"));
//                // Получение списка параметров данной продукции
//                List<ProductParameter> parameters = ProductParameterRepository.getById(productId);
//                product.setParameters(parameters);
//                products.add(product);
//            }
//        }
//        return products;
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
}