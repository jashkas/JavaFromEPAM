package chapter13.entity;

public class Parameter {
    private int id;
    private String name;
    private String unit;
    private int paramGroupId;

    public Parameter() { }

    public Parameter(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public Parameter(String name, String unit, int paramGroupId) {
        this.name = name;
        this.unit = unit;
        this.paramGroupId = paramGroupId;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getParamGroupId() {
        return paramGroupId;
    }

    public void setParamGroupId(int paramGroupId) {
        this.paramGroupId = paramGroupId;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", paramGroupId=" + paramGroupId +
                '}';
    }
}
