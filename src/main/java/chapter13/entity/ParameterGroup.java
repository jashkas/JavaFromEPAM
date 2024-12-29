package chapter13.entity;

import java.util.List;

public class ParameterGroup {
    private int id;
    private String name;
    private int productGroupId;
    private List<Parameter> parameters;

    public ParameterGroup() { }

    public ParameterGroup(String name, int productGroupId, List<Parameter> parameters) {
        this.name = name;
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

    public int getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(int productGroupId) {
        this.productGroupId = productGroupId;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "ParameterGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productGroupId=" + productGroupId +
                ", parameters=" + parameters +
                '}';
    }
}
