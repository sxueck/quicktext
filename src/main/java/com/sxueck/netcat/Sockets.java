package com.sxueck.netcat;

import com.sxueck.linktext.Link;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("InfiniteLoopStatement")
public class Sockets {

    private int PORT;
    private final Link linkedContent = new Link();

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
                String[] Cons = linkedContent.PrintContent(10);
                for (String con : Cons) {
                    if (con != null) {
                        os.println(con);
                    }
                }
            } else {
                linkedContent.PushContent(line);
                os.flush();
            }
        }
        os.flush();
        is.close();
        os.close();
    }
}
