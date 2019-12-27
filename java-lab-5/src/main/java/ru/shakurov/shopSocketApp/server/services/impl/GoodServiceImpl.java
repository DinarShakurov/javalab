package ru.shakurov.shopSocketApp.server.services.impl;

import ru.shakurov.shopSocketApp.server.context.Component;
import ru.shakurov.shopSocketApp.server.dto.GoodDto;
import ru.shakurov.shopSocketApp.server.dto.ListDto;
import ru.shakurov.shopSocketApp.server.model.Good;
import ru.shakurov.shopSocketApp.server.repositories.GoodRepository;
import ru.shakurov.shopSocketApp.server.services.GoodService;

import java.util.List;

public class GoodServiceImpl implements GoodService, Component {
    private GoodRepository goodRepository;

    @Override
    public boolean buyGood(long userId, long goodId) {
        return goodRepository.buyGood(userId, goodId);
    }

    @Override
    public ListDto<GoodDto> getAllGoods() {
        List<Good> list = goodRepository.findAll();
        return ListDto.from(list, GoodDto::from);
    }

    @Override
    public boolean addGood(String name, Integer price) {
        return goodRepository.addGood(name, price);
    }

    @Override
    public String getName() {
        return "GoodService";
    }
}
