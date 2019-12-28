package ru.shakurov.webapp_javalab.dto;

public class SuccessDto implements Dto {
    private String message;

    public String getMessage() {
        return message;
    }

    public SuccessDto setMessage(String message) {
        this.message = message;
        return this;
    }
}
