package ru.shakurov.shopSocketApp.server.controller;

import ru.shakurov.shopSocketApp.server.constants.Constant;
import ru.shakurov.shopSocketApp.server.context.Component;
import ru.shakurov.shopSocketApp.server.dto.TokenDto;
import ru.shakurov.shopSocketApp.server.dto.UserDto;
import ru.shakurov.shopSocketApp.server.protocol.jwt.JwtRequest;
import ru.shakurov.shopSocketApp.server.protocol.jwt.JwtResponse;
import ru.shakurov.shopSocketApp.server.protocol.jwt.token.JwtTokenCoder;
import ru.shakurov.shopSocketApp.server.protocol.jwt.token.JwtTokenCoderAuth0Based;
import ru.shakurov.shopSocketApp.server.services.AuthService;

public class AuthController implements Component {

    private AuthService service;

    public void singIn(JwtRequest request, JwtResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserDto userDto = service.singIn(login, password);

        JwtTokenCoder jwtTokenCoder = new JwtTokenCoderAuth0Based(Constant.SUPER_SECRET_KEY);
        String token = jwtTokenCoder.encode(userDto.getId().toString(), userDto.getRole());

        TokenDto tokenDto = new TokenDto(token);
        response.setData(tokenDto);
    }

    @Override
    public String getName() {
        return "AuthController";
    }
}
