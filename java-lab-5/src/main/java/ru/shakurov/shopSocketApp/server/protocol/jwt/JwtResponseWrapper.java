package ru.shakurov.shopSocketApp.server.protocol.jwt;

import ru.shakurov.shopSocketApp.server.dto.Dto;

public class JwtResponseWrapper<D extends Dto> implements JwtResponse {

    private String header;
    private D payload;
    private Integer errorCode;

    public JwtResponseWrapper(String header) {
        this.header = header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;

    }

    public String getHeader() {
        return header;
    }

    public D getPayload() {
        return payload;
    }

    public int getErrorCode() {
        return errorCode;
    }


    @Override
    public <E extends Dto> void setData(E data) {
        this.payload = (D) data;
    }
}
