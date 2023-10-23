package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8989;

    public static void main(String[] args) {
        // Создание объекта сервера (передаем порт в качестве аргумента)
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started!");

            while (true) {
                // Ожидаем подключения. О факте подключения сообщает серверный сокет методом accept()
                // Как только он услышит что-то, сразу передаст это в клиентский сокет
                // Также в ресурсах try создадим 2 потока - ввода и вывода (в их качестве могут быть разные,
                // воспользуемся для примера PrintWriter и BufferedReader
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream()))) {
                    // Как только получаем сообщение - выведем его на печать
                    System.out.println("Client's message: " + in.readLine() +
                            ", got over port N: " + clientSocket.getPort());
                    out.println("Message printed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
