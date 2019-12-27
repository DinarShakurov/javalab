package ru.shakurov.shopSocketApp.server.dto;

public class TokenDto implements Dto {
    private String token;

    public TokenDto(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public TokenDto setToken(String token) {
        this.token = token;
        return this;
    }
}
