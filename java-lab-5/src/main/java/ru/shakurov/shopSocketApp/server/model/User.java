package ru.shakurov.shopSocketApp.server.model;

import ru.shakurov.shopSocketApp.server.dto.UserDto;

public class User {
    private Long id;
    private String login;
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public static UserDto from(User user){
        return new UserDto()
                .setRole(user.getRole())
                .setLogin(user.getLogin())
                .setId(user.getId());
    }
}

