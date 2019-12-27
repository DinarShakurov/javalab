package ru.shakurov.shopSocketApp.server.controller;

import ru.shakurov.shopSocketApp.server.context.Component;
import ru.shakurov.shopSocketApp.server.dto.GoodDto;
import ru.shakurov.shopSocketApp.server.dto.ListDto;
import ru.shakurov.shopSocketApp.server.protocol.jwt.JwtRequest;
import ru.shakurov.shopSocketApp.server.protocol.jwt.JwtResponse;
import ru.shakurov.shopSocketApp.server.protocol.jwt.token.DecodedJwtToken;
import ru.shakurov.shopSocketApp.server.services.GoodService;
import ru.shakurov.shopSocketApp.server.utils.CheckAccess;
import ru.shakurov.shopSocketApp.server.dto.SuccessDto;

public class GoodController implements Component {
    private GoodService goodService;

    public void getAllGoods(JwtRequest req, JwtResponse resp) {
        DecodedJwtToken token = req.getDecodedToken();

        CheckAccess.allowUserAndAdmin(token.getRole());

        ListDto<GoodDto> listDto = goodService.getAllGoods();
        resp.setData(listDto);
    }

    public void buyGood(JwtRequest req, JwtResponse resp) {
        DecodedJwtToken token = req.getDecodedToken();

        CheckAccess.allowUser(token.getRole());

        long userId = Long.parseLong(token.getSubject());
        long goodId = Long.parseLong(req.getParameter("id"));
        if (!goodService.buyGood(userId, goodId))
            throw new IllegalStateException("Ошибка при покупке");

        resp.setData(new SuccessDto().setMessage("Successfully"));
    }

    public void addGood(JwtRequest req, JwtResponse resp) {
        DecodedJwtToken token = req.getDecodedToken();

        CheckAccess.allowAdmin(token.getRole());

        long userId = Long.parseLong(token.getSubject());
        String name = req.getParameter("name");
        Integer price = Integer.parseInt(req.getParameter("price"));

        if (!goodService.addGood(name, price))
            throw new IllegalStateException("Something goes wrong during adding good");
        resp.setData(new SuccessDto().setMessage("Successfully"));
    }

    @Override
    public String getName() {
        return "ProductController";
    }
}
