package chapter13.entity;

import java.util.List;

public class ProductGroup {
    private int id;
    private String name;
    private List<Product> products;

    public ProductGroup() { }

    public ProductGroup(String name, List<Product> products) {
        this.name = name;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
