package ru.andronov;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.andronov.services.InputMessageServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket(Constants.HOST, Constants.PORT);
        LOG.info("Connected to server");

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
