package ru.shakurov.shopSocketApp.server.protocol.jwt;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import ru.shakurov.shopSocketApp.server.protocol.Request;
import ru.shakurov.shopSocketApp.server.protocol.Response;
import ru.shakurov.shopSocketApp.server.protocol.StringParser;
import ru.shakurov.shopSocketApp.server.protocol.jwt.token.DecodedJwtToken;
import ru.shakurov.shopSocketApp.server.protocol.jwt.token.JwtTokenCoder;

import java.io.IOException;

public class JsonParserJacksonBased implements StringParser {
    private JwtTokenCoder coder;

    public JsonParserJacksonBased(JwtTokenCoder coder) {
        this.coder = coder;
    }

    @Override
    public String toJson(Response response) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Request buildRequest(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ObjectNode objectNode = mapper.readValue(json, ObjectNode.class);
            TextNode headerNode = (TextNode) objectNode.get("header");
            ObjectNode payloadNode = (ObjectNode) objectNode.get("payload");
            DecodedJwtToken token = coder.decode(payloadNode.get("token").asText());

            return new JwtRequestWrapper(headerNode, payloadNode, token);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }
}
