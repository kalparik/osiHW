package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый -
        // рекомендуем использовать около 8080
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            try {
                Socket clientSocket = serverSocket.accept(); // ждем подключения
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                System.out.println("New connection accepted");
                final String name = in.readLine(); // Читаем строку
                // Выводим её на экран вместе с номером порта клиента, с которого пришло соединение
                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            } finally {
                System.out.println("closing...");
                serverSocket.close();
            }
        }
    }
}