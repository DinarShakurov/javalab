package ru.shakurov.shopSocketApp.server.repositories;

import ru.shakurov.shopSocketApp.server.model.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Integer> {
    Optional<User> fineByLogin(String login);
}
