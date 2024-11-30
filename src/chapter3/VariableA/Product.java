package chapter3.VariableA;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

// TODO: Ошибка начат не тот вариант
public class Product {
    private int id;
    private String name;
    private BigInteger upc;
    private String manufacturer;
    private int price;
    private Date shelfLife;
    private int amount;
    //
    // конструкторы
    //
    // пустой продукт
    public Product() {
        id = -1;
        name = "";
        upc = new BigInteger("0");
        manufacturer = "";
        price = -1;
        amount = -1;
    }
    // продукт инициализируется переданными аргументами
    public Product(int id, String name, BigInteger upc, String manufacturer, int price, Date shelfLife, int amount) {
        this.id = id;
        this.name = name;
        this.upc = upc;
        this.manufacturer = manufacturer;
        this.price = price;
        this.shelfLife = shelfLife;
        this.amount = amount;
    }
    // продукт с рандомными значениями некоторых полей (id, upc, shelfLife)
    public Product(String name, String manufacturer, int price, int amount) {
        Random random = new Random();
        this.id = random.nextInt();
        this.name = name;
        this.upc = new BigInteger(256, random);  // рандомное число длиной 256 бит
        this.manufacturer = manufacturer;
        this.price = price;
        //this.shelfLife =
        this.amount = amount;
    }
    //
    // геттеры
    //
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public BigInteger getUpc() {
        return upc;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public int getPrice() {
        return price;
    }
    public Date getShelfLife() {
        return shelfLife;
    }
    public int getAmount() {
        return amount;
    }
    //
    // сеттеры
    //
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUPC(BigInteger upc) {
        this.upc = upc;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static void main(String[] args) {
        Product product = new Product();
    }
}
