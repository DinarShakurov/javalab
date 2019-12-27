package ru.shakurov.shopSocketApp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonParser {
    public static String parse(String message, String token) {
        String[] args = message.split(" ");
        ObjectMapper mapper = new ObjectMapper();
        JsonNodeFactory factory = mapper.getNodeFactory();
        switch (args[0]) {
            case "/sign_in":
                ObjectNode payload = factory.objectNode();
                payload.set("login", factory.textNode(args[1]));
                payload.set("password", factory.textNode(args[2]));
                ObjectNode objectNode = factory.objectNode();
                objectNode.set("header", factory.textNode("Sigh in"));
                objectNode.set("payload", payload);
                return objectNode.toString();
            case "/get_all":
                ObjectNode objectNode1 = factory.objectNode();
                objectNode1.set("header", factory.textNode("Get all goods"));
                ObjectNode payload1 = factory.objectNode();
                payload1.set("token", factory.textNode(token));
                objectNode1.set("payload", payload1);
                return objectNode1.toString();
            case "/buy_good":
                ObjectNode objectNode2 = factory.objectNode();
                objectNode2.set("header", factory.textNode("Buy good"));
                ObjectNode payload2 = factory.objectNode();
                payload2.set("token", factory.textNode(token));
                payload2.set("id", factory.textNode(args[1]));
                objectNode2.set("payload", payload2);
                return objectNode2.toString();
            case "/add_good":
                ObjectNode objectNode3 = factory.objectNode();
                objectNode3.set("header", factory.textNode("Add good"));
                ObjectNode payload3 = factory.objectNode();
                payload3.set("name", factory.textNode(args[1]));
                payload3.set("price", factory.textNode(args[2]));
                objectNode3.set("payload", payload3);
                return objectNode3.toString();
            default:
                return "";
        }
    }
}
