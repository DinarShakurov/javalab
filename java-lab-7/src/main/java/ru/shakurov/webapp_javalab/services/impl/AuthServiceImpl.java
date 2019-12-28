package ru.shakurov.webapp_javalab.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.shakurov.context.Component;
import ru.shakurov.webapp_javalab.dto.UserDto;
import ru.shakurov.webapp_javalab.model.User;
import ru.shakurov.webapp_javalab.repositories.UsersRepository;
import ru.shakurov.webapp_javalab.services.AuthService;

import java.util.Optional;

public class AuthServiceImpl implements AuthService, Component {
    UsersRepository usersRepository;

    @Override
    public UserDto signIn(String login, String password) {
        Optional<User> user = usersRepository.fineByLogin(login);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword()))
            throw new IllegalStateException("Incorrect login or password");
        UserDto userDto = UserDto.from(user.get());
        return userDto;
    }

    @Override
    public boolean signUp(String login, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordBCrypt = passwordEncoder.encode(password);
        User user = new User()
                .setLogin(login)
                .setRole("user")
                .setPassword(passwordBCrypt);
        if (!usersRepository.addUser(user)) {
            throw new IllegalStateException("Something goes wrong");
        }
        return true;
    }

    @Override
    public String getName() {
        return "AuthService";
    }

}
