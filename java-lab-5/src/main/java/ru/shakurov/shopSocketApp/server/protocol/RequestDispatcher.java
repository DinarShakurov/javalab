package ru.shakurov.shopSocketApp.server.protocol;

public interface RequestDispatcher {
    void doDispatch(Request req, Response res);
}
