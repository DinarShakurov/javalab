package ru.shakurov.shopSocketApp.server.protocol.jwt;

import ru.shakurov.shopSocketApp.server.controller.AuthController;
import ru.shakurov.shopSocketApp.server.controller.GoodController;
import ru.shakurov.shopSocketApp.server.protocol.Request;
import ru.shakurov.shopSocketApp.server.protocol.RequestDispatcher;
import ru.shakurov.shopSocketApp.server.protocol.Response;

public class JwtRequestDispatcher implements RequestDispatcher {

    private AuthController authController;
    private GoodController goodController;

    public JwtRequestDispatcher(AuthController authController, GoodController goodController) {
        this.authController = authController;
        this.goodController = goodController;
    }

    @Override
    public void doDispatch(Request req, Response resp) {
        String command = req.getCommand();
        JwtRequest request = (JwtRequest) req;
        JwtResponse response = (JwtResponse) resp;
        switch (command) {
            case "Sign in" -> authController.singIn(request, response);
            case "Buy good" -> goodController.buyGood(request, response);
            case "Add good" -> goodController.addGood(request, response);
            case "Get all goods" -> goodController.getAllGoods(request, response);
            default -> throw new IllegalStateException("Unknown command");
        }

    }
}
