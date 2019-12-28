package ru.shakurov.webapp_javalab.services.impl;

import ru.shakurov.context.Component;
import ru.shakurov.webapp_javalab.dto.GoodDto;
import ru.shakurov.webapp_javalab.dto.ListDto;
import ru.shakurov.webapp_javalab.model.Good;
import ru.shakurov.webapp_javalab.repositories.GoodRepository;
import ru.shakurov.webapp_javalab.services.GoodService;

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
    public ListDto<GoodDto> getMyGoods(Long id) {
        List<Good> list = goodRepository.findAllById(id);
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
