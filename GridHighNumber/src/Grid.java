import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.*;

import java.awt.event.*;

public class Grid extends JFrame implements MouseListener {
    private static final long serialVersionUID = 1L;

    private int width;
    private int height;
    private int margin_top;
    private int margin_bottom;
    private int margin_left;
    private int margin_right;

    private int nb_collumns;
    private int nb_rows;

    private Game grid;

    public Grid()
    {
        this.width = 600;
        this.height = 600;
        this.margin_top = 40;
        this.margin_bottom = this.margin_top;
        this.margin_left = 40;
        this.margin_right = this.margin_left;
        this.nb_collumns = 5;
        this.nb_rows = 5;
        this.grid = new Game(this.nb_collumns, this.nb_rows);

        addMouseListener(this);

        setSize(this.width, this.height);
        setVisible(true);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
    }

    public void clickSquare(int x, int y)
    {
        int rectW = this.getSize().width - (this.margin_left + this.margin_right);
        int rectH = this.getSize().height - (this.margin_top + this.margin_bottom);
        
        if(x >= this.margin_left && x < (this.margin_left + rectW) && y >= this.margin_top && y < (this.margin_top + rectH))
        {
            int mouseClickedX = (x - this.margin_left) / (rectW / this.nb_collumns);
            int mouseClickedY = (y - this.margin_top) / (rectH / this.nb_rows);
            System.out.println("Clicked: (" + mouseClickedX + " : " + mouseClickedY + ")");
            this.grid.play(mouseClickedX, mouseClickedY);
        }
        else
        {
            System.out.println("Out of bound!");
        }
    }

    public void paint(Graphics g)
    {
        // Dimmensions Rectangle contenaire
        int rectW = this.getSize().width - (this.margin_left + this.margin_right);
        int rectH = this.getSize().height - (this.margin_top + this.margin_bottom);

        // Clear background
        super.paint(g);

        // Draw Rectangle contenaire
        g.drawRect(this.margin_left, this.margin_top, rectW, rectH);
        // Collones ||
        for(int i = 1; i < this.nb_collumns; i++)
        {
            g.drawLine(this.margin_left + ((rectW / this.nb_collumns) * i), this.margin_top, this.margin_left + ((rectW / this.nb_collumns) * i), this.margin_top + rectH);
        }
        
        // Lignes =
        for(int i = 1; i < this.nb_rows; i++)
        {
            g.drawLine(this.margin_left, this.margin_top + ((rectH / this.nb_rows) * i), this.margin_left + rectW, this.margin_top + ((rectH / this.nb_rows) * i));
        }

        // Contenu de la grille
        Square[][] tempGrid = this.grid.get_grid();
        for(int x = 0; x < this.nb_collumns; x++)
        {
            for(int y = 0; y < this.nb_rows; y++)
            {
                if(!tempGrid[x][y].is_hidden())
                {
                    int play_x = this.margin_left + (x * (rectW / this.nb_collumns) + ((rectW / this.nb_collumns) / 2));
                    int play_y = this.margin_top + (y * (rectH / this.nb_rows)) + ((rectH / this.nb_rows) / 2);
                    g.drawString(Integer.toString(tempGrid[x][y].get_val()), play_x, play_y);
                }
            }
        }

        if(this.grid.is_GameOver())
        {
            g.drawString("GAME OVER: " + this.grid.get_nb_plays() + " Clicks!", this.margin_left, this.margin_top / 2);
        }
        else
        {
            g.drawString(this.grid.get_nb_plays() + " Clicks!", this.margin_left, this.margin_top / 2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        this.clickSquare(arg0.getX(), arg0.getY());
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
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