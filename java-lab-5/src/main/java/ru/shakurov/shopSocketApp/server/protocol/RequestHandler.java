package ru.shakurov.shopSocketApp.server.protocol;

public interface RequestHandler {
    Response handleRequest(Request req);
}
