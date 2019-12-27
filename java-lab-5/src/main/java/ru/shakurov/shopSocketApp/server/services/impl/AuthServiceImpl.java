package ru.shakurov.shopSocketApp.server.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.shakurov.shopSocketApp.server.context.Component;
import ru.shakurov.shopSocketApp.server.dto.UserDto;
import ru.shakurov.shopSocketApp.server.exceipions.UserNotFoundedException;
import ru.shakurov.shopSocketApp.server.model.User;
import ru.shakurov.shopSocketApp.server.repositories.UsersRepository;
import ru.shakurov.shopSocketApp.server.services.AuthService;

import java.util.Optional;

public class AuthServiceImpl implements Component, AuthService {
    private UsersRepository usersRepository;

    @Override
    public UserDto singIn(String login, String password) {
        Optional<User> optionalUser = usersRepository.fineByLogin(login);
        if (optionalUser.isPresent()) {
            PasswordEncoder passEnc = new BCryptPasswordEncoder();
            if (passEnc.matches(password, optionalUser.get().getPassword()))
                return User.from(optionalUser.get());
        }
        throw new UserNotFoundedException("User with login \"" + login + "\" not founded");
    }

    @Override
    public String getName() {
        return "AuthService";
    }
}
