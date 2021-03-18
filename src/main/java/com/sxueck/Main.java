package com.sxueck;

import com.sxueck.netcat.Sockets;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    Sockets ss = new Sockets();
	    ss.SetPort(8919);
	    ss.Start();
    }
}
