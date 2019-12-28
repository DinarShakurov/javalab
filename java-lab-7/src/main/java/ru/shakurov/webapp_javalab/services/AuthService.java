package ru.shakurov.webapp_javalab.services;

import ru.shakurov.webapp_javalab.dto.UserDto;
import ru.shakurov.webapp_javalab.model.User;

public interface AuthService {
    UserDto signIn(String login, String password);

    boolean signUp(String login, String password);
}
