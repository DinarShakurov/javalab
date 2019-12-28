package ru.shakurov.webapp_javalab.services;


import ru.shakurov.webapp_javalab.dto.GoodDto;
import ru.shakurov.webapp_javalab.dto.ListDto;


public interface GoodService {
    boolean buyGood(long userId, long goodId);

    ListDto<GoodDto> getAllGoods();
    ListDto<GoodDto> getMyGoods(Long id);

    boolean addGood(String name, Integer price);
}
