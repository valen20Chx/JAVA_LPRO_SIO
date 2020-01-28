// package src.client.network;

import java.net.Socket;
import java.util.Vector;
import java.awt.Point;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;

public class ReadThread extends Thread {
    private Socket socket;
    private Vector<NetFlag> inputFlagBuffer;
    private Vector<Point> inputPointBuffer;
    private Vector<Integer> inputBrushBuffer;
    private Vector<Color> inputColorBuffer;
    private Vector<String> inputStringBuffer;

    private boolean running;

    public ReadThread(Socket socket) {
        this.socket = socket;
        this.inputPointBuffer = new Vector<Point>();
        this.inputBrushBuffer = new Vector<Integer>();
        this.inputColorBuffer = new Vector<Color>();
        this.inputFlagBuffer = new Vector<NetFlag>();
        this.inputStringBuffer = new Vector<String>();

        this.running = true;
    }

    public void run() {
        while (this.running) {
            try {
                DataInputStream is = new DataInputStream(this.socket.getInputStream());
                NetFlag flag = NetFlag.getFlag(is.readInt());
                boolean correctFlag = true;
                switch(flag) {
                    case QUIT:
                        this.running = false;
                        break;
                    case POINT:
                        this.inputPointBuffer.add(this.readPoint(is));
                        break;
                    case COLOR:
                        this.inputColorBuffer.add(this.readColor(is));
                        break;
                    case STRING:
                        int len = is.readInt(); // Gets the length of the string
                        this.inputStringBuffer.add(this.readString(is, len));
                        break;
                    case BRUSH:
                        this.inputBrushBuffer.add(is.readInt());
                        break;
                    default:
                        System.out.println("NetFlag recieved unknowed: " + flag);
                        correctFlag = false;
                        break;
                }
                if(correctFlag) {
                    this.inputFlagBuffer.add(flag);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void close() {
        this.running = false;
    }

    public Point pullPoint() {
        Point temp = null;
        if(!this.inputPointBuffer.isEmpty()) {
            temp = this.inputPointBuffer.firstElement();
            this.inputPointBuffer.removeElementAt(0);
        }
        return temp;
    }

    public NetFlag pullFlag() {
        NetFlag temp = null;
        if(!this.inputFlagBuffer.isEmpty()) {
            temp = this.inputFlagBuffer.firstElement();
            this.inputFlagBuffer.removeElementAt(0);
        }
        return temp;
    }
    
    public Color pullColor() {
        Color temp = null;
        if(!this.inputColorBuffer.isEmpty()) {
            temp = this.inputColorBuffer.firstElement();
            this.inputColorBuffer.removeElementAt(0);
        }
        return temp;
    }
    
    public String pullString() {
        String temp = null;
        if(!this.inputStringBuffer.isEmpty()) {
            temp = this.inputStringBuffer.firstElement();
            this.inputStringBuffer.removeElementAt(0);
        }
        return temp;
    }

    public Integer pullBrush() {
        Integer temp = null;
        if(!this.inputBrushBuffer.isEmpty()) {
            temp = this.inputBrushBuffer.firstElement();
            this.inputBrushBuffer.removeElementAt(0);
        }
        return temp;
    }

    private int readInt(DataInputStream is) throws IOException {
        return is.readInt();
    }

    private Point readPoint(DataInputStream is) throws IOException {
        return new Point(is.readInt(), is.readInt());
    }

    private String readString(DataInputStream is, int length) throws IOException {
        String rString = new String();
        for (int i = 0; i < length; i++) {
            rString += is.readChar();
        }
        return rString;
    }

    private Color readColor(DataInputStream is) throws IOException {
        return new Color(is.readInt());
    }
}