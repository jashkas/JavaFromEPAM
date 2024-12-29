package chapter13.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private String description;
    private LocalDate releaseDate;

    private int productGroupId;
    private List<ProductParameter> parameters = new ArrayList<>();

    public Product() { }

    public Product(String name, String description, LocalDate releaseDate, int productGroupId) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.productGroupId = productGroupId;
    }

    public Product(String name, String description, LocalDate releaseDate, int productGroupId, List<ProductParameter> parameters) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.productGroupId = productGroupId;
        this.parameters = parameters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(int productGroupId) {
        this.productGroupId = productGroupId;
    }

    public List<ProductParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ProductParameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", productGroupId=" + productGroupId +
                ", parameters=" + parameters +
                '}';
    }
}
