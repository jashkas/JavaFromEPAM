package chapter13.entity;

import java.util.List;

public class ParameterGroup {
    private int id;
    private String name;
    private List<Parameter> parameters;

    public ParameterGroup(String name, List<Parameter> parameters) {
        this.name = name;
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

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
