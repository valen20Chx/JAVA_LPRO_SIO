// package src.client.network;

import java.net.Socket;
import java.util.Vector;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadThread extends Thread {
    private Socket socket;
    private Vector<Point> inputPointBuffer;
    private boolean running;

    public ReadThread(Socket socket) {
        this.socket = socket;
        this.inputPointBuffer = new Vector<Point>();
        this.running = true;
    }

    public void run() {
        while (this.running) {
            try {
                ObjectInputStream is = new ObjectInputStream(this.socket.getInputStream());
                this.inputPointBuffer.add((Point)is.readObject());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public Point pullPoint() {
        Point temp = null;
        if(!this.inputPointBuffer.isEmpty()) {
            temp = this.inputPointBuffer.firstElement();
            this.inputPointBuffer.removeElementAt(0);
        }
        return temp;
    }
}