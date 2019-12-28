package ru.shakurov.webapp_javalab.repositories;


import ru.shakurov.webapp_javalab.model.Good;

import java.util.List;

public interface GoodRepository extends CrudRepository<Good, Integer> {
    boolean buyGood(long userId, long goodId);

    boolean addGood(String name, Integer price);

    List<Good> findAllById(Long id);
}
