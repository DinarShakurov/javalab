package ru.shakurov.shopSocketApp.server.protocol;

public interface StringParser{
    String toJson(Response response);

    Request buildRequest(String json);
}
