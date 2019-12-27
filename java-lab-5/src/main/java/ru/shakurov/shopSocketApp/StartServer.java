package ru.shakurov.shopSocketApp;

import ru.shakurov.shopSocketApp.server.context.ApplicationContext;
import ru.shakurov.shopSocketApp.server.context.ApplicationContextBasedOnReflection;
import ru.shakurov.shopSocketApp.server.server_socket.Server;

public class StartServer {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContextBasedOnReflection();
        Server server = new Server(applicationContext);
        server.start(7777);
    }
}
