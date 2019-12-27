package ru.shakurov.shopSocketApp.server.protocol.jwt;

import ru.shakurov.shopSocketApp.server.dto.Dto;
import ru.shakurov.shopSocketApp.server.dto.ErrorDto;
import ru.shakurov.shopSocketApp.server.protocol.Request;
import ru.shakurov.shopSocketApp.server.protocol.RequestHandler;
import ru.shakurov.shopSocketApp.server.protocol.Response;

public class JwtRequestHandler implements RequestHandler {

    private JwtRequestDispatcher dispatcher;

    public JwtRequestHandler(JwtRequestDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }


    //TODO обработать с помощью try-catch
    @Override
    public Response handleRequest(Request req) {
        JwtResponseWrapper<Dto> resp = new JwtResponseWrapper<>(req.getCommand());
        try {
            dispatcher.doDispatch(req, resp);
        } catch (Exception e) {
            resp.setErrorCode(0);
            ErrorDto errorDto = new ErrorDto(e.getMessage());
            resp.setData(errorDto);
        }
        return resp;
    }
}
