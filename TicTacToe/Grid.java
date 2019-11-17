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

    private TicTacToe grid;

    public Grid()
    {
        this.width = 600;
        this.height = 600;
        this.margin_top = 40;
        this.margin_bottom = this.margin_top;
        this.margin_left = 40;
        this.margin_right = this.margin_left;
        this.nb_collumns = 3;
        this.nb_rows = 3;
        this.grid = new TicTacToe();

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

        // Mouse was clicked
        for(int i = 0; i < this.grid.get_play_size(); i++)
        {
            Point play_i = this.grid.get_point_at_play_index(i);
            int play_x = this.margin_left + (play_i.x * (rectW / this.nb_collumns));
            int play_y = this.margin_top + (play_i.y * (rectH / this.nb_rows));

            // If i even draw X
            if(i % 2 == 0)
            {
                // Draw the X
                g.drawLine(play_x, play_y, play_x + (rectW / this.nb_collumns), play_y + (rectH / this.nb_rows));
                g.drawLine(play_x, play_y + (rectH / this.nb_rows), play_x + (rectW / this.nb_collumns), play_y);
            }
            // Else draw circle
            else
            {
                g.drawOval(play_x, play_y, (rectW / this.nb_collumns), (rectH / this.nb_rows));
            }
        }

        if(this.grid.is_game_finished())
        {
            g.drawString("Player " + (this.grid.get_player_won() == 1 ? "X" : "O") + " Won", this.margin_left, this.margin_top / 2);
        }
        else
        {
            g.drawString("Player " + (this.grid.get_play_size() % 2 == 0 ? "X" : "O") + " Turn", this.margin_left, this.margin_top / 2);
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