package ru.shakurov.shopSocketApp.server.repositories;

import ru.shakurov.shopSocketApp.server.model.Good;

public interface GoodRepository extends CrudRepository<Good, Integer> {
    boolean buyGood(long userId, long goodId);

    boolean addGood(String name, Integer price);
}
