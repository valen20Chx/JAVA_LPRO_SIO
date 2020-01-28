import java.net.Socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Point;

class Client {
    private Socket socket;
    private int number;
    
    public Client (String server, int port) {
        try {
            this.socket = new Socket(server, port);
        } catch (IOException e) {
            System.out.println("ServerSocket Error: " + e.getMessage());
        }

        DataInputStream dis = null;
        try {
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(dis != null) {
            try {
                this.number = dis.readInt();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void sendPlay(Point play) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(this.socket.getOutputStream());
            os.writeObject(play);
        } catch(IOException e) {
            System.out.println("Error ObjectOutputStream: " + e.getMessage());
        }
    }

    public int getTurn() {
        return this.number;
    }

    public Point readPlay() {
        Point point = null;
        try {
            ObjectInputStream is = new ObjectInputStream(this.socket.getInputStream());
            point = (Point) is.readObject();
        } catch(IOException e) {
            System.out.println("Error ObjectOutputStream: " + e.getMessage());
        } catch(ClassNotFoundException e) {
            System.out.println("Error ClassNotFoundExeption: " + e.getMessage());
        }

        return point;
    }

    public void close() {
        try {
            this.socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean isClosed() {
        return this.socket.isClosed();
    }
}