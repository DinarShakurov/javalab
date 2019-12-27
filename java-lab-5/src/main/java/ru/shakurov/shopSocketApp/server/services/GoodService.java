package ru.shakurov.shopSocketApp.server.services;

import ru.shakurov.shopSocketApp.server.dto.ListDto;
import ru.shakurov.shopSocketApp.server.dto.GoodDto;


public interface GoodService {
    boolean buyGood(long userId, long goodId);

    ListDto<GoodDto> getAllGoods();

    boolean addGood(String name, Integer price);
}
