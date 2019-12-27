package ru.shakurov.shopSocketApp.server.protocol.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtTokenCoderAuth0Based extends JwtTokenCoder {

    public JwtTokenCoderAuth0Based(String key) {
        super(key);
    }

    @Override
    public String encode(String sub, String role) {
        return JWT.create()
                .withSubject(sub)
                .withClaim("rol", role)
                .sign(Algorithm.HMAC256(key));
    }

    @Override
    public DecodedJwtToken decode(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key)).build();
        DecodedJWT jwt = verifier.verify(token);
        return new DecodedJwtTokenAuth0Based(jwt);
    }
}
