package ru.shakurov.shopSocketApp.server.server_socket;

import ru.shakurov.shopSocketApp.server.constants.Constant;
import ru.shakurov.shopSocketApp.server.context.ApplicationContext;
import ru.shakurov.shopSocketApp.server.controller.AuthController;
import ru.shakurov.shopSocketApp.server.controller.GoodController;
import ru.shakurov.shopSocketApp.server.protocol.*;
import ru.shakurov.shopSocketApp.server.protocol.jwt.JsonParserJacksonBased;
import ru.shakurov.shopSocketApp.server.protocol.jwt.JwtRequestDispatcher;
import ru.shakurov.shopSocketApp.server.protocol.jwt.JwtRequestHandler;
import ru.shakurov.shopSocketApp.server.protocol.jwt.token.JwtTokenCoderAuth0Based;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private ApplicationContext applicationContext;
    private RequestHandler handler;
    private StringParser jsonParser;

    private List<ClientHandler> clients;
    private ServerSocket serverSocket;


    public Server(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.jsonParser = new JsonParserJacksonBased(new JwtTokenCoderAuth0Based(Constant.SUPER_SECRET_KEY));
        AuthController authController = applicationContext.getComponent(AuthController.class, "AuthController");
        GoodController goodController = applicationContext.getComponent(GoodController.class, "ProductController");
        handler = new JwtRequestHandler(new JwtRequestDispatcher(authController, goodController));
    }


    public void start(Integer port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        while (true) {
            try (ClientHandler clientHandler = new ClientHandler(serverSocket.accept())) {
                clientHandler.start();
                clients.add(clientHandler);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }


    private class ClientHandler extends Thread implements AutoCloseable {
        private Socket socket;
        private PrintWriter toClient;
        private BufferedReader fromClient;


        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                this.toClient = new PrintWriter(socket.getOutputStream(), true);
                this.fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public void run() {
            String input;
            try {
                while (true) {
                    if (((input = fromClient.readLine()) != null)) {
                        if (input.equals("*")) break;

                        System.out.print("New request: ");
                        System.out.println(input);

                        Request request = jsonParser.buildRequest(input);
                        Response response = handler.handleRequest(request);
                        toClient.println(jsonParser.toJson(response));
                        System.out.println("Response  is: " + jsonParser.toJson(response));
                    }

                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public void close() throws Exception {
            toClient.close();
            fromClient.close();
            socket.close();
        }
    }
}

