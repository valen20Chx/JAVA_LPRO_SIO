// package src.server;

import java.io.IOException;
import java.net.ServerSocket;

import java.awt.Point;
import java.awt.Color;

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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            this.running = false;
            e.printStackTrace();
        }
    }

    public void loop() {
        while(this.running) {

            NetFlag flag = this.client1.readThread.pullFlag();

            if(flag != null) {
                switch (flag) {
                    case POINT:
                        Point tempPoint = this.client1.readThread.pullPoint();
                        if(tempPoint != null) {
                            this.client2.writeThread.pushPoint(tempPoint);
                            this.client2.writeThread.pushFlag(NetFlag.POINT);
                            System.out.println("Cli1 -> Cli2 : " + tempPoint);
                        }
                        break;
                    case BRUSH:
                        Integer tempBrush = this.client1.readThread.pullBrush();
                        if(tempBrush != null) {
                            this.client2.writeThread.pushBrush(tempBrush);
                            this.client2.writeThread.pushFlag(NetFlag.BRUSH);
                            System.out.println("Cli1 -> Cli2 Brush : " + tempBrush);
                        }
                        break;
                    case COLOR:
                        Color tempColor = this.client1.readThread.pullColor();
                        if(tempColor != null) {
                            this.client2.writeThread.pushColor(tempColor);
                            this.client2.writeThread.pushFlag(NetFlag.COLOR);
                            System.out.println("Cli1 -> Cli2 : " + tempColor);
                        }
                        break;
                    case STRING:
                        String tempString = this.client1.readThread.pullString();
                        if(tempString != null) {
                            this.client2.writeThread.pushString(tempString);
                            this.client2.writeThread.pushFlag(NetFlag.STRING);
                            System.out.println("Cli1 -> Cli2 : " + tempString);
                        }
                        break;
                    case QUIT:
                        this.client1.close();
                        this.client2.close();
                        this.socket.close();
                        this.running = false;
                        break;
                    default:
                        break;
                }
            }

            flag = this.client2.readThread.pullFlag();

            if(flag != null) {
                switch (flag) {
                    case POINT:
                        Point tempPoint = this.client2.readThread.pullPoint();
                        if(tempPoint != null) {
                            this.client1.writeThread.pushPoint(tempPoint);
                            this.client1.writeThread.pushFlag(NetFlag.POINT);
                            System.out.println("Cli1 -> Cli2 : " + tempPoint);
                        }
                        break;
                    case BRUSH:
                        Integer tempBrush = this.client2.readThread.pullBrush();
                        if(tempBrush != null) {
                            this.client1.writeThread.pushBrush(tempBrush);
                            this.client1.writeThread.pushFlag(NetFlag.BRUSH);
                            System.out.println("Cli1 -> Cli2 Brush : " + tempBrush);
                        }
                        break;
                    case COLOR:
                        Color tempColor = this.client2.readThread.pullColor();
                        if(tempColor != null) {
                            this.client1.writeThread.pushColor(tempColor);
                            this.client1.writeThread.pushFlag(NetFlag.COLOR);
                            System.out.println("Cli1 -> Cli2 : " + tempColor);
                        }
                        break;
                    case STRING:
                        String tempString = this.client2.readThread.pullString();
                        if(tempString != null) {
                            this.client1.writeThread.pushString(tempString);
                            this.client1.writeThread.pushFlag(NetFlag.STRING);
                            System.out.println("Cli1 -> Cli2 : " + tempString);
                        }
                        break;
                    case QUIT:   
                        this.client1.close();
                        this.client2.close();
                        this.socket.close();
                        this.running = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main server = new Main(3300);
        server.loop();
    }
}