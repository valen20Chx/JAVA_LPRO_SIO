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

    private boolean myTurn;

    private Client socket;

    public Grid() {
        this.width = 600;
        this.height = 600;
        this.margin_top = 80;
        this.margin_bottom = this.margin_top;
        this.margin_left = 40;
        this.margin_right = this.margin_left;
        this.nb_collumns = 3;
        this.nb_rows = 3;
        this.grid = new TicTacToe();

        this.socket = new Client("localhost", 3070);
        if(this.socket.getTurn() == 1) {
            this.myTurn = true;
        }
        else {
            this.myTurn = false;
        }

        System.out.println("You are player: " + this.socket.getTurn());

        this.addMouseListener(this);

        this.setSize(this.width, this.height);
        this.setVisible(true);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(d.width / 2 - this.getWidth() / 2, d.height / 2 - this.getHeight() / 2);

        this.setTitle("TicTacToe | Player " + this.socket.getTurn());
        
        if(this.myTurn) {
            System.out.println("It's your turn to play.");    
        } else {
            System.out.println("It's the turn of player " + (this.socket.getTurn() == 1 ? 2 : 1) + ".");

            Point play = this.socket.readPlay();
            System.out.println("Recieved (" + play.x + " : " + play.y + ") from server");

            this.playSquare(new Point(play.x, play.y));
            this.repaint();

            System.out.println("It's your turn to play.");
            this.myTurn = true;
        }
    }

    public void clickSquare(int x, int y) {
        int rectW = this.getSize().width - (this.margin_left + this.margin_right);
        int rectH = this.getSize().height - (this.margin_top + this.margin_bottom);

        if (x >= this.margin_left && x < (this.margin_left + rectW) && y >= this.margin_top
                && y < (this.margin_top + rectH)) {
            int mouseClickedX = (x - this.margin_left) / (rectW / this.nb_collumns);
            int mouseClickedY = (y - this.margin_top) / (rectH / this.nb_rows);
            this.playSquare(new Point(mouseClickedX, mouseClickedY));
        } else {
            System.out.println("Out of bound!");
        }
    }

    private void playSquare(Point play) {
        System.out.println("Played: (" + play.x + " : " + play.y + ")");
        boolean playRes = this.grid.play(play.x, play.y);
        this.paint(this.getGraphics());

        if (this.myTurn && playRes) {
            this.socket.sendPlay(new Point(play.x, play.y));
            if(!this.grid.is_game_finished()) {
                System.out.println("It's the turn of player " + (this.socket.getTurn() == 1 ? 2 : 1) + ".");
                this.myTurn = false;
    
                play = this.socket.readPlay();
                System.out.println("Recieved (" + play.x + " : " + play.y + ") from server");
    
                this.playSquare(new Point(play.x, play.y));
                this.paint(this.getGraphics());
    
                System.out.println("It's your turn to play.");
                this.myTurn = true;
            }
        }
        if(this.grid.is_game_finished() && !this.socket.isClosed()) {
            this.socket.sendPlay(new Point(-1, 0));
            this.socket.close();
        }
    }

    public void paint(Graphics g) {
        System.out.println("Painting...");
        // Dimmensions Rectangle contenaire
        int rectW = this.getSize().width - (this.margin_left + this.margin_right);
        int rectH = this.getSize().height - (this.margin_top + this.margin_bottom);

        // Clear background
        super.paint(g);

        // Draw Rectangle contenaire
        g.drawRect(this.margin_left, this.margin_top, rectW, rectH);
        // Collones ||
        for (int i = 1; i < this.nb_collumns; i++) {
            g.drawLine(this.margin_left + ((rectW / this.nb_collumns) * i), this.margin_top,
                    this.margin_left + ((rectW / this.nb_collumns) * i), this.margin_top + rectH);
        }

        // Lignes =
        for (int i = 1; i < this.nb_rows; i++) {
            g.drawLine(this.margin_left, this.margin_top + ((rectH / this.nb_rows) * i), this.margin_left + rectW,
                    this.margin_top + ((rectH / this.nb_rows) * i));
        }

        // Mouse was clicked
        for (int i = 0; i < this.grid.get_play_size(); i++) {
            Point play_i = this.grid.get_point_at_play_index(i);
            int play_x = this.margin_left + (play_i.x * (rectW / this.nb_collumns));
            int play_y = this.margin_top + (play_i.y * (rectH / this.nb_rows));

            // If i even draw X
            if (i % 2 == 0) {
                // Draw the X
                g.drawLine(play_x, play_y, play_x + (rectW / this.nb_collumns), play_y + (rectH / this.nb_rows));
                g.drawLine(play_x, play_y + (rectH / this.nb_rows), play_x + (rectW / this.nb_collumns), play_y);
            }
            // Else draw circle
            else {
                g.drawOval(play_x, play_y, (rectW / this.nb_collumns), (rectH / this.nb_rows));
            }
        }

        if (this.grid.is_game_finished()) {
            if(this.grid.get_player_won() == 0) {
                g.drawString("It's a tie!", this.margin_left,
                        this.getHeight() - (this.margin_bottom / 2));

            } else {
                g.drawString("Player " + (this.grid.get_player_won() == 1 ? "X" : "O") + " Won", this.margin_left,
                        this.getHeight() - (this.margin_bottom / 2));
            }
        } else {
            g.drawString("Player " + (this.grid.get_play_size() % 2 == 0 ? "X" : "O") + " Turn", this.margin_left,
            this.getHeight() - (this.margin_bottom / 2));
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (this.myTurn) {
            this.clickSquare(arg0.getX(), arg0.getY());
        }
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