package ru.shakurov.shopSocketApp.server.protocol.jwt.token;

import com.auth0.jwt.interfaces.DecodedJWT;

public class DecodedJwtTokenAuth0Based implements DecodedJwtToken {
    private DecodedJWT decodedJWT;

    public DecodedJwtTokenAuth0Based(DecodedJWT decodedJWT) {
        this.decodedJWT = decodedJWT;
    }

    @Override
    public String getSubject() {
        return decodedJWT.getSubject();
    }

    @Override
    public String getRole() {
        return decodedJWT.getClaim("rol").asString();
    }

    @Override
    public String getType() {
        return decodedJWT.getType();
    }

    @Override
    public String getAlgorithm() {
        return decodedJWT.getAlgorithm();
    }
}
