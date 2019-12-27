package ru.shakurov.shopSocketApp.server.exceipions;

public class UserNotFoundedException extends RuntimeException {

    public UserNotFoundedException(String message) {
        super(message);
    }
}
