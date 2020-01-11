import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

public class Dessin1 extends JFrame implements MouseListener
{
    private static final long serialVersionUID = 1L;
    private int x;
    private int xp;
    private int y;
    private int yp;
    public Dessin1()
    {
        addMouseListener(this);
        setSize(600, 600);
        setVisible(true);

        this.xp = -1;
        this.yp = -1;

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
    }

    void affect(int x, int y)
    {
        this.x = x;
        this.y = y;

        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.affect(e.getPoint().x, e.getPoint().y);
    }

    public void paint(Graphics g)
    {
        if(this.xp != -1 && this.yp != -1)
        {
            g.drawLine(this.xp, this.yp, this.x, this.y);
        }
        this.xp = this.x;
        this.yp = this.y;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}