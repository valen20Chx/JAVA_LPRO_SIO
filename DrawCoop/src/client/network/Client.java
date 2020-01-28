// package src.client.network;

import java.io.IOException;
import java.net.Socket;
// import java.util.Vector;
// import java.awt.Point;

public class Client extends Thread {
    private Socket socket;

    public ReadThread readThread;
    public WriteThread writeThread;

    public Client(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.readThread = new ReadThread(this.socket);
            this.writeThread = new WriteThread(this.socket);
            this.readThread.start();
            this.writeThread.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run() {}

    public void close() {
        this.writeThread.close();
        this.readThread.close();
        try {
            this.socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}