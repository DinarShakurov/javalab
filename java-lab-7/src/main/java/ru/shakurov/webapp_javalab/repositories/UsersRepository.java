package ru.shakurov.webapp_javalab.repositories;


import ru.shakurov.webapp_javalab.model.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Integer> {
    Optional<User> fineByLogin(String login);

    boolean addUser(User user);
}
