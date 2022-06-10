package ru.andronov.services;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class InputMessageServiceImpl implements InputMessageService {
    private final BufferedReader reader;

    @SneakyThrows
    public InputMessageServiceImpl(Socket socket) {
        this.reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));;
    }


    @SneakyThrows
    @Override
    public void run() {
        String line = "";
        while (true) {
            line = reader.readLine();
            if (line != null) {
                System.out.println(line);
                System.out.flush();
            }
        }
    }
}
