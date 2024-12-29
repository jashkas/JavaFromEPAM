package chapter13.entity;

public class ProductParameter {
    private String nameParam;
    private String unitParam;

    private int productId;
    private int paramId;
    private String valueParam;

    public ProductParameter() { }

    public ProductParameter(String nameParam, String unitParam, String valueParam) {
        this.nameParam = nameParam;
        this.unitParam = unitParam;
        this.valueParam = valueParam;
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
