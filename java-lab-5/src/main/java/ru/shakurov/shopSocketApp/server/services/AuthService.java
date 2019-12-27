package ru.shakurov.shopSocketApp.server.services;

import ru.shakurov.shopSocketApp.server.dto.UserDto;

public interface AuthService {
    UserDto singIn(String login, String password);
}
