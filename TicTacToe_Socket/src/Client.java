import java.net.Socket;
import java.net.UnknownHostException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(dos != null) {
            try {
                dos.writeInt(play.x);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                dos.writeInt(play.y);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public int getTurn() {
        return this.number;
    }

    public Point readPlay() {
        DataInputStream dis = null;
        Point point = new Point();
        try {
            dis = new DataInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(dis != null) {
            try {
                point.x = dis.readInt();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                point.y = dis.readInt();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return new Point(point.x, point.y);
    }
}