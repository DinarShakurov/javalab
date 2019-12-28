package ru.shakurov.webapp_javalab.dto;

public class ErrorDto implements Dto{
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
