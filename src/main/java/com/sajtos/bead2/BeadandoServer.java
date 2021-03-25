package com.sajtos.bead2;

import com.sajtos.bead2.util.FileHandler;

import java.net.*;
import java.io.*;

public class BeadandoServer {
    Thread serverThread = null;
    FileHandler fileHandler = null;



    public void start(int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port:" + serverSocket.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileHandler = new FileHandler();

        ServerSocket finalServerSocket = serverSocket;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    handleConnections(finalServerSocket);
                }
            }
        };

        serverThread = new Thread(runnable);
        serverThread.start();
    }

    public void stop() {
        serverThread.interrupt();
    }

    private void handleConnections(ServerSocket serverSocket) {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            System.out.println("New client connected from " + socket.getInetAddress() + ":" + socket.getPort());

            BufferedReader serverSocketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String messageString = extractMessage(serverSocketInput);

            System.out.println(messageString);
            fileHandler.write(messageString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractMessage(BufferedReader serverSocketInput) {
        StringBuilder fullMessage = new StringBuilder();
        try {
            String messagePart = "";
            do {
                messagePart = serverSocketInput.readLine();
                if (messagePart != null) {
                    fullMessage.append(messagePart);
                }
            } while (messagePart != null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullMessage.toString();
    }

}
