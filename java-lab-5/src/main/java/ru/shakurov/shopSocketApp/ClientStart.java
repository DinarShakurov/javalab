package ru.shakurov.shopSocketApp;

import ru.shakurov.shopSocketApp.client.client_socket.Client;

import java.io.IOException;
import java.util.Scanner;

public class ClientStart {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Client client = new Client();
        try {
            client.start("localhost", 7777);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        while (true) {
            client.sendMessage(in.nextLine());
        }
    }
}
