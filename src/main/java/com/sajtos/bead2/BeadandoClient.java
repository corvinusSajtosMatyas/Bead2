package com.sajtos.bead2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BeadandoClient {
    public void writeToServer(String server, int port, String text) {
        Socket socket = null;
        try {
            socket = new Socket(server, port);

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(text);

        } catch (IOException e) {

        }
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
