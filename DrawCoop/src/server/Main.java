// package src.server;

import java.io.IOException;
import java.net.ServerSocket;

import java.awt.Point;

public class Main {
    private Client client1;
    private Client client2;
    private ServerSocket socket;
    private boolean running;

    public Main(int port) {
        this.running = true;
        try {
            this.socket = new ServerSocket(port);
            this.client1 = new Client(this.socket.accept());
            this.client2 = new Client(this.socket.accept());
            this.client1.start();
            this.client2.start();
            this.loop();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            this.running = false;
            e.printStackTrace();
        }
    }

    public void loop() {
        while(this.running) {
            Point temp = this.client1.readThread.pullPoint();
            this.client2.writeThread.pushPoint(temp);
            if(temp != null) {
                System.out.println("Cli1 -> Cli2 : " + temp);
            }

            temp = this.client2.readThread.pullPoint();
            this.client1.writeThread.pushPoint(temp);
            
            if(temp != null) {
                System.out.println("Cli2 -> Cli1 : " + temp);
            }
        }
    }

    public static void main(String[] args) {
        Main server = new Main(3300);
    }
}