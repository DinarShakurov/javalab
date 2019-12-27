package ru.shakurov.shopSocketApp.client.client_socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.shakurov.shopSocketApp.client.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Клиент нормального белого человека (нет)
 */
public class Client extends Thread {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String token;

    public void start(String ip, Integer port) throws IOException {
        socket = new Socket(ip, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        token = "";
    }

    public void sendMessage(String message) {
        String json = JsonParser.parse(message, token);
        this.out.println(json);
    }


    //Черновой вариант
    private Runnable receiverMessage = () -> {
        while (true) {
            try {
                String line = in.readLine();
                System.out.println(line);
                ObjectNode objectNode = new ObjectMapper().readValue(line, ObjectNode.class);
                if (token.equals("")) {
                    this.token = objectNode.get("payload").get("token").asText();
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    };
}
