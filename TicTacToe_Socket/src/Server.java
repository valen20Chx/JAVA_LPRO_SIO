import java.net.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

import java.awt.Point;

class Server {
    private Socket player1;
    private Socket player2;
    private ServerSocket socket;

    public Server (int port) {
        try {
            System.out.println("Launching server on port " + port);
            this.socket = new ServerSocket(port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Server was launched");
        
        try {
            System.out.println("Waiting for player 1 to connect.");
            this.player1 = this.socket.accept();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Player 1 has connected.");
        
        try {
            System.out.println("Waiting for player 2 to connect.");
            this.player2 = this.socket.accept();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Player 2 has connected.");
        
        this.sendTurns();
    }

    private void sendTurns() {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(player1.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(dos != null) {
            try {
                dos.writeInt(1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            dos = new DataOutputStream(player2.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(dos != null) {
            try {
                dos.writeInt(2);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void sendPlay(Point play, int player) {
        DataOutputStream dos = null;
        switch (player) {
            case 1:
            try {
                dos = new DataOutputStream(player1.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
                break;
            case 2:
            try {
                dos = new DataOutputStream(player2.getOutputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                break;
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

    public Point readPlay(int player) {
        DataInputStream dis = null;
        Point point = new Point();
        switch (player) {
            case 1:
            try {
                dis = new DataInputStream(this.player1.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                break;
            case 2:
            try {
                dis = new DataInputStream(this.player2.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                break;
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