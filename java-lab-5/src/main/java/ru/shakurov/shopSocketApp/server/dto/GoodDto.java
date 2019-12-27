package ru.shakurov.shopSocketApp.server.dto;

import ru.shakurov.shopSocketApp.server.model.Good;

public class GoodDto implements Dto {
    private String name;
    private int price;

    public GoodDto(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public GoodDto setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public GoodDto setPrice(int price) {
        this.price = price;
        return this;
    }



    public static GoodDto from(Good good){
        return new GoodDto(good.getName(), good.getPrice());
    }
}
