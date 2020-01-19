import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.*;

import java.awt.event.*;

public class Drawing extends JFrame implements MouseListener {
    private static final long serialVersionUID = 1L;

    private int width;
    private int height;
    private int margin_top;
    private int margin_bottom;
    private int margin_left;
    private int margin_right;

    private Point point1;
    private Point point2;
    private int lineWidth;

    private boolean drawing;
    private boolean mouseInWindow;

    private Dimension winSize;

    public Drawing()
    {
        this.width = 600;
        this.height = 600;
        this.margin_top = 40;
        this.margin_bottom = this.margin_top;
        this.margin_left = 40;
        this.margin_right = this.margin_left;

        this.point1 = new Point(0,0);
        this.point2 = new Point(0,0);
        this.lineWidth = 10;

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
            // // Top
            // g.drawLine(this.point1.x, this.point1.y - 1, this.point2.x, this.point2.y - 1);
            // // Bottom
            // g.drawLine(this.point1.x, this.point1.y + 1, this.point2.x, this.point2.y + 1);
            // // Left
            // g.drawLine(this.point1.x - 1, this.point1.y, this.point2.x - 1, this.point2.y);
            // // Right
            // g.drawLine(this.point1.x + 1, this.point1.y, this.point2.x + 1, this.point2.y);
            
            for(int i = 0; i < this.lineWidth; i++) {
                Point coefDir = new Point(Math.abs(this.point1.x - this.point2.x), Math.abs(this.point1.y - this.point2.y));
                Point normalCoefDir = new Point((coefDir.x == 0 ? 0 : coefDir.y / coefDir.x), (coefDir.y == 0 ? 0 : coefDir.x / coefDir.y));
                Point tempPoint1 = new Point(this.point1.x + (i + (normalCoefDir.x)), this.point1.y + (i + (normalCoefDir.y)));
                Point tempPoint2 = new Point(this.point2.x + (i + (normalCoefDir.x)), this.point2.y + (i + (normalCoefDir.y)));
                
                g.drawLine(tempPoint1.x, tempPoint1.y, tempPoint2.x, tempPoint2.y);
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