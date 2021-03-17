package com.sxueck.netcat;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.net.ServerSocket;

public class Sockets {
    public void StartServer() {
        int port = 8919;
        int len;
        try {
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();
            Reader reader = new InputStreamReader(socket.getInputStream());
            char[] chars = new char[1024];
            StringBuilder builder = new StringBuilder();
            while ((len = reader.read(chars)) != -1) {
                builder.append(new String(chars, 0, len));
            }
            System.out.println("Receive from client message=: " + builder);
            reader.close();
            socket.close();
            server.close();
        } catch (Exception ignored) {

        }
    }
}
