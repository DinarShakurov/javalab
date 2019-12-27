package ru.shakurov.shopSocketApp.server.protocol;

public interface Request {
    String getCommand();

    String getParameter(String name);

}
