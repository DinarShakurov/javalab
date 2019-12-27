package ru.shakurov.shopSocketApp.server.protocol.jwt.token;

public abstract class JwtTokenCoder {
    protected String key;

    public JwtTokenCoder(String key) {
        this.key = key;
    }

    public abstract String encode(String sub, String role);

    public abstract DecodedJwtToken decode(String token);
}
