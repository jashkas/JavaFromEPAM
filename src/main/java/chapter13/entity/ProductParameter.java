package chapter13.entity;

import chapter13.repository.ParameterRepository;

import java.sql.SQLException;

public class ProductParameter {
    private String nameParam;
    private String unitParam;

    private int productId;
    private int paramId;
    private String valueParam;

    public ProductParameter() { }

    public ProductParameter(String nameParam, String unitParam, int productId, int paramId, String valueParam) {
        this.nameParam = nameParam;
        this.unitParam = unitParam;
        this.productId = productId;
        this.paramId = paramId;
        this.valueParam = valueParam;
    }

    public ProductParameter(int productId, int paramId, String valueParam) throws SQLException {
        this.productId = productId;
        this.paramId = paramId;
        this.valueParam = valueParam;
        Parameter parameter = ParameterRepository.getById(paramId);
        this.nameParam = parameter.getName();
        this.unitParam = parameter.getUnit();
    }

    public String getNameParam() {
        return nameParam;
    }

    public void setNameParam(String nameParam) {
        this.nameParam = nameParam;
    }

    public String getUnitParam() {
        return unitParam;
    }

    public void setUnitParam(String unitParam) {
        this.unitParam = unitParam;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getParamId() {
        return paramId;
    }

    public void setParamId(int paramId) {
        this.paramId = paramId;
    }

    public String getValueParam() {
        return valueParam;
    }

    public void setValueParam(String valueParam) {
        this.valueParam = valueParam;
    }

    @Override
    public String toString() {
        return "ProductParameter{" +
                "nameParam='" + nameParam + '\'' +
                ", unitParam='" + unitParam + '\'' +
                ", productId=" + productId +
                ", paramId=" + paramId +
                ", valueParam='" + valueParam + '\'' +
                '}';
    }
}
