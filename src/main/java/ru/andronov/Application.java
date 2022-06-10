package ru.andronov;

import lombok.SneakyThrows;
import ru.andronov.services.InputMessageServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Application {

    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket(Constants.HOST, Constants.PORT);

        new Thread(new InputMessageServiceImpl(socket)).start();

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        while (true) {
            String message = consoleReader.readLine();
            writer.println(message);
            writer.flush();
        }
    }
}
