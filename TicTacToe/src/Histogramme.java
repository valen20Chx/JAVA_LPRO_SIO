import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;

public class Histogramme extends JFrame
{
    public Histogramme()
    {
        setSize(600, 600);
        setVisible(true);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
    }

    double f(double x)
    {
        return (Math.cos(x / 5) + Math.sin(x / 7) * this.getSize().height / 4);
    }

    public void paint(Graphics g)
    {
        for(int x = 0; x < this.getSize().width; x += 20)
        {
            g.drawLine(x, this.getSize().height, x, (int) (this.f(x) / 2));
        }
    }
}