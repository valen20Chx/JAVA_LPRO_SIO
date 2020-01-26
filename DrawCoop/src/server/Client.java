// package src.server;

import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {
    private Socket socket;
    public ReadThread readThread;
    public WriteThread writeThread;

    Client(Socket socket) {
        try {
            this.socket = socket;
            this.readThread = new ReadThread(this.socket);
            this.writeThread = new WriteThread(this.socket);
            this.readThread.start();
            this.writeThread.start();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void close() {
        this.readThread.close();
        this.writeThread.close();
        this.socket.close();
    }
}