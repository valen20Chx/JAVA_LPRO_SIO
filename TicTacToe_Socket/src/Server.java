import java.net.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        switch (player) {
            case 1:
                try {
                    ObjectOutputStream os = new ObjectOutputStream(this.player1.getOutputStream());
                    os.writeObject(play);
                } catch(IOException e) {
                    System.out.println("Error ObjectOutputStream: " + e.getMessage());
                }
                break;
            case 2:
                try {
                    ObjectOutputStream os = new ObjectOutputStream(this.player2.getOutputStream());
                    os.writeObject(play);
                } catch(IOException e) {
                    System.out.println("Error ObjectOutputStream: " + e.getMessage());
                }
                break;
        }
    }
    

    public Point readPlay(int player) {
        Point point = new Point();
        switch (player) {
            case 1:
                try {
                    if(!player1.isClosed()) {
                        // dis = new DataInputStream(this.player1.getInputStream());
                        ObjectInputStream is = new ObjectInputStream(this.player1.getInputStream());
                        point = (Point) is.readObject();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch(ClassNotFoundException e) {
                    System.out.println("Error ClassNotFoundExeption: " + e.getMessage());
                }
                break;
            case 2:
                try {
                    if(!player1.isClosed()) {
                        // dis = new DataInputStream(this.player1.getInputStream());
                        ObjectInputStream is = new ObjectInputStream(this.player2.getInputStream());
                        point = (Point) is.readObject();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch(ClassNotFoundException e) {
                    System.out.println("Error ClassNotFoundExeption: " + e.getMessage());
                }
                break;
        }

        
        if(point.x == -1) {
            switch (player) {
                case 1:
                    try {
                        player1.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        player2.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
            }
        }

        return point;
    }

    public boolean isClosed(int player) {
        boolean res = false;
        switch(player) {
            case 1:
                res = this.player1.isClosed();
                break;
            case 2:
                res = this.player2.isClosed();
                break;
        }
        return res;
    }
}