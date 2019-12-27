package ru.shakurov.shopSocketApp.server.protocol.jwt;

import ru.shakurov.shopSocketApp.server.protocol.Request;
import ru.shakurov.shopSocketApp.server.protocol.jwt.token.DecodedJwtToken;

public interface JwtRequest extends Request {
    DecodedJwtToken getDecodedToken();
}
