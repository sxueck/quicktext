package com.sxueck.netcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Sockets {

    private int PORT;

    public void SetPort(int port) {
        this.PORT = port;
    }

    public void Start() throws IOException {
        System.out.printf("=> listen on 0.0.0.0:%d\n", PORT);
        ServerSocket server = new ServerSocket(PORT);

        for (; ; ) {
            try (Socket clientSocket = server.accept()) {
                handleNewClient(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleNewClient(Socket clientSocket) throws IOException {
        BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintStream os = new PrintStream(clientSocket.getOutputStream());
        String line;
        while ((line = is.readLine()) != null) {
            if (line.equalsIgnoreCase("QUIT")) {
                break;
            } else if (line.equalsIgnoreCase("PRINT")) {
                os.println(line);
            } else {
                System.out.println(line);
                os.println("successfully submitted");
                os.flush();
            }
        }
        os.flush();
        is.close();
        os.close();
    }
}
