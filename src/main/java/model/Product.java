package model;

public class Product {
    private int id;
    private String name;
    private int price;
    private int amount;
    private String color;
    private Category category;

    public Product() {
    }

    public Product(int id, String name, int price, int amount, String color, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.color = color;
        this.category = category;
    }

    public Product(String name, int price, int amount, String color) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.color = color;
    }

    public Product(int id, String name, int price, int amount, String color) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.color = color;
    }

    public Product(String name, int price, int amount, String color, Category category) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.color = color;
        this.category = category;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
