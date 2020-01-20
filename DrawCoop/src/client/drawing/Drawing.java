package src.client.drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.*;

import java.awt.event.*;
import java.util.Vector;

public class Drawing extends JFrame implements MouseListener {
    private static final long serialVersionUID = 1L;

    private int width;
    private int height;

    private Point point1;
    private Point point2;
    // private int lineWidth;

    private boolean drawing;
    private boolean mouseInWindow;

    private Dimension winSize;

    public Drawing()
    {
        this.width = 600;
        this.height = 600;

        this.point1 = new Point(0,0);
        this.point2 = new Point(0,0);
        // this.lineWidth = 10;

        this.drawing = false;
        this.mouseInWindow = false;

        this.winSize = new Dimension();

        this.setBackground(new Color(255, 255, 255));

        addMouseListener(this);

        setSize(this.width, this.height);
        setVisible(true);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
    }

    public void loop() {
        while(this.isVisible()){
            this.repaint();
        }
    }

    public void paint(Graphics g)
    {
        // Dimmensions Rectangle contenaire
        // int rectW = this.getSize().width - (this.margin_left + this.margin_right);
        // int rectH = this.getSize().height - (this.margin_top + this.margin_bottom);

        // 
        if(this.getSize().width != this.winSize.width || this.getSize().height != this.winSize.height) {    
            // Clear background
            super.paint(g);
            this.winSize = this.getSize();
        }
        // Draw Rectangle contenaire
        // g.drawRect(this.margin_left, this.margin_top, rectW, rectH);
        if(this.drawing && this.mouseInWindow) {
            this.point2 = this.getMousePosition();

            // Create Brush
            Brush drawBrush = new PencilBrush(this.point1, this.point2);

            // Get Points Arrays
            Vector<Point> points1 = drawBrush.getPoints(1);
            Vector<Point> points2 = drawBrush.getPoints(2);
            
            for(int i = 0; i < points1.size(); i++) {
                g.drawLine(points1.get(i).x, points1.get(i).y, points2.get(i).x, points2.get(i).y);
            }

            System.out.println("(" + this.point1.x + " : " + this.point1.y + ") -> (" + this.point2.x + " : " + this.point2.y + ")");
            this.point1 = new Point(this.point2.x, this.point2.y);
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