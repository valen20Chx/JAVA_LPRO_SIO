// package src.client.drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.*;

import java.awt.event.*;
import java.time.Clock;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Timer;
import java.util.Vector;

public class Drawing extends JFrame implements MouseListener {
    private static final long serialVersionUID = 1L;

    private int width;
    private int height;

    private Point point1;
    private Point point2;
    // private int lineWidth;

    private Point sockPoint1;
    private Point sockPoint2;

    private boolean drawing;
    private boolean mouseInWindow;

    private Dimension winSize;

    private int framerate;

    private Client socket;

    public Drawing()
    {
        this.width = 600;
        this.height = 600;

        this.point1 = null;
        this.point2 = null;
        this.sockPoint1 = null;
        this.sockPoint2 = null;

        // this.lineWidth = 10;

        this.drawing = false;
        this.mouseInWindow = false;

        this.winSize = new Dimension();

        this.framerate = 30;

        this.socket = new Client("localhost", 3300);
        this.socket.start();
        
        this.setBackground(new Color(255, 255, 255));

        addMouseListener(this);

        setSize(this.width, this.height);
        setVisible(true);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
    }

    public void loop() {
        Long now = System.currentTimeMillis();
        Long lastFrame = System.currentTimeMillis();

        while(this.isVisible()){
            // Set Time
            now = System.currentTimeMillis();
            Long duration = now - lastFrame;
            if(duration < (1000 / this.framerate)) {
                Long delta = (1000 / this.framerate) - duration;
                try {
                    Thread.sleep(delta);
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            NetFlag flag = this.socket.readThread.pullFlag();

            if(flag != null) {
                switch (flag) {
                    case POINT:
                        // System.out.println("Recieved a point");
                        if(this.sockPoint1 == null) {
                            this.sockPoint1 = this.socket.readThread.pullPoint();
                        } else if(this.sockPoint2 == null) {
                            this.sockPoint2 = this.socket.readThread.pullPoint();
                            this.repaint();
                        }
                        break;
                    default:
                        break;
                }
            }
            
            this.repaint();
            
            lastFrame = System.currentTimeMillis();
        }
    }

    public void paint(Graphics g)
    {
        if(this.getSize().width != this.winSize.width || this.getSize().height != this.winSize.height) {    
            // Clear background
            super.paint(g);
            this.winSize = this.getSize();
        }

        if(this.drawing && this.mouseInWindow) {
            this.point2 = this.getMousePosition();

            // Create Brush
            Brush drawBrush = new PencilBrush(new Point(this.point1.x, this.point1.y), new Point(this.point2.x, this.point2.y));
            this.socket.writeThread.pushFlag(NetFlag.POINT);
            this.socket.writeThread.pushPoint(new Point(this.point1.x, this.point1.y));
            this.socket.writeThread.pushFlag(NetFlag.POINT);
            this.socket.writeThread.pushPoint(new Point(this.point2.x, this.point2.y));

            // Get Points Arrays
            Vector<Point> points1 = drawBrush.getPoints(1);
            Vector<Point> points2 = drawBrush.getPoints(2);

            // System.out.println("Points 1 : " + points1);
            // System.out.println("Points 2 : " + points2);
            
            for(int i = 0; i < points1.size(); i++) {
                g.drawLine(points1.get(i).x, points1.get(i).y, points2.get(i).x, points2.get(i).y);
            }

            // System.out.println("(" + this.point1.x + " : " + this.point1.y + ") -> (" + this.point2.x + " : " + this.point2.y + ")");
            this.point1 = this.point2;
        }

        if(this.sockPoint1 != null && this.sockPoint2 != null) {
            Brush drawBrush = new PencilBrush(new Point(this.sockPoint1.x, this.sockPoint1.y), new Point(this.sockPoint2.x, this.sockPoint2.y));

            // Get Points Arrays
            Vector<Point> points1 = drawBrush.getPoints(1);
            Vector<Point> points2 = drawBrush.getPoints(2);

            // System.out.println("Points 1 : " + points1);
            // System.out.println("Points 2 : " + points2);
            
            for(int i = 0; i < points1.size(); i++) {
                g.drawLine(points1.get(i).x, points1.get(i).y, points2.get(i).x, points2.get(i).y);
            }
            // reset
            this.sockPoint1 = null;
            this.sockPoint2 = null;
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        this.mouseInWindow = true;
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        this.mouseInWindow = false;
        System.out.println("mouseExited");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        this.drawing = true;
        this.point1 = this.getMousePosition();
        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        this.drawing = false;
        System.out.println("mouseReleased");
    }
}