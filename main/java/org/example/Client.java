package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    // Задаем порт, к которому хотим присоединиться.
    // Через какой порт клиент это будет делать у себя - нам не важно
    private static final int PORT = 8989;

    // Также задаем адрес, где находится наш сервер (в данном случае на нашем локальном компьютере)
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()))) {
            // В данном случае out - это просто переменная, которая отправляет что-то на сервер
            out.println("Hello Server!");
            System.out.println("Ответ сервера: " + in.readLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
