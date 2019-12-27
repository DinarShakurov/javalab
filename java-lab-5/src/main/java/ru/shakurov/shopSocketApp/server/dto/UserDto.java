package ru.shakurov.shopSocketApp.server.dto;

import ru.shakurov.shopSocketApp.server.model.User;

public class UserDto implements Dto{
    Long id;
    String login;
    String role;

    public String getRole() {
        return role;
    }

    public UserDto setRole(String role) {
        this.role = role;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public static UserDto from(User user){
        return new UserDto()
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setRole(user.getRole());
    }
}
