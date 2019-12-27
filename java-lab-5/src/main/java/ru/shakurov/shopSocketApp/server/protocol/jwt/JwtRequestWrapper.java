package ru.shakurov.shopSocketApp.server.protocol.jwt;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import ru.shakurov.shopSocketApp.server.protocol.jwt.token.DecodedJwtToken;

public class JwtRequestWrapper implements JwtRequest {
    private TextNode headerNode;
    private ObjectNode payload;
    private DecodedJwtToken token;

    public JwtRequestWrapper(TextNode headerNode, ObjectNode payload, DecodedJwtToken token) {
        this.headerNode = headerNode;
        this.payload = payload;
        this.token = token;
    }

    @Override
    public DecodedJwtToken getDecodedToken() {
        return token;
    }

    @Override
    public String getCommand() {
        return headerNode.asText();
    }

    @Override
    public String getParameter(String name) {
        return payload.get(name).asText();
    }
}
