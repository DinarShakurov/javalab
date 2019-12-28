package ru.shakurov.webapp_javalab.model;

public class Good {
    private String name;
    private int price;
    private long id;

    public String getName() {
        return name;
    }

    public Good setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Good setPrice(int price) {
        this.price = price;
        return this;
    }

    public long getId() {
        return id;
    }

    public Good setId(long id) {
        this.id = id;
        return this;
    }
}
