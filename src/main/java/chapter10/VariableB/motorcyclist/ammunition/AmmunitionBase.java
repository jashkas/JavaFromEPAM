package chapter10.VariableB.motorcyclist.ammunition;

import java.io.Serializable;

public abstract class AmmunitionBase implements Serializable {
    private String name;
    private String type;
    private double price;
    private transient double weight;

    public AmmunitionBase(String name, String type, double price, double weight) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.weight = weight;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public double getWeight() { return weight; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Gear{название='" + name + "', тип='" + type + "', вес=" + weight + ", цена=" + price + "}";
    }
}
