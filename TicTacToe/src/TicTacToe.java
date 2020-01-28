import java.util.Vector;
import java.awt.Point;

public class TicTacToe {

    private int[][] grid;
    private Vector<Point> play_v;
    private int nb_collumns;
    private int nb_rows;
    private int MAX_PLAYS;
    private int player_won;
    private Integer PLAYER_X;
    private Integer PLAYER_O;
    private int nb_align;
    private BotEasy ennemy;

    // Constructor
    public TicTacToe()
    {
        this.nb_collumns = 3;
        this.nb_rows = 3;
        this.MAX_PLAYS = this.nb_rows * this.nb_collumns;
        this.grid = new int[this.nb_collumns][this.nb_rows];
        this.player_won = 0;
        this.PLAYER_X = new Integer(1);
        this.PLAYER_O = new Integer(2);
        this.nb_align = this.nb_collumns;
        this.play_v = new Vector<Point>(0);
        this.ennemy = new BotEasy(this.PLAYER_O.intValue(), this.nb_collumns, this.nb_rows);

        for(int i = 0; i < this.nb_collumns; i++)
        {
            for(int j = 0; j < this.nb_rows; j++)
            {
                this.grid[i][j] = 0;
            }
        }
    }

    public int get_player_won()
    {
        return this.player_won;
    }

    public boolean is_game_finished()
    {
        return (this.player_won != 0 ? true : false);
    }

    public int get_play_size()
    {
        return this.play_v.size();
    }

    public int get_play_at_coord(int x, int y)
    {
        return this.grid[x][y];
    }

    public int get_play_at_index(int i)
    {
        return this.grid[this.play_v.get(i).x][this.play_v.get(i).y];
    }

    public Point get_point_at_play_index(int i)
    {
        return new Point(this.play_v.get(i).x,this.play_v.get(i).y);
    }

    public void play(int x, int y)
    {
        if(x >= 0 && x < 3 && y >= 0 && y < 3)
        {
            if(this.is_game_finished())
            {
                System.out.println("Game already over");
            }
            else
            {
                System.out.println("Played: (" + x + " : " + y + ")");
                boolean exist = false;
                for(int i = 0; i < this.play_v.size(); i++)
                {
                    if(x == this.play_v.get(i).x && y == this.play_v.get(i).y)
                    {
                        exist = true;
                        System.out.println("Position Existe deja");
                    }
                }
                if(!exist && this.play_v.size() < MAX_PLAYS)
                {
                    this.grid[x][y] = (this.play_v.size() % 2 == 0 ? this.PLAYER_X : this.PLAYER_O);
                    this.play_v.add(new Point(x, y));
                    System.out.println("Position Correcte et enregistre");
                    this.update_game_state();
                    // Bot play
                    if(this.play_v.size() % 2 != 0)
                    {
                        Point ennemy_play = this.ennemy.play(this.grid); 
                        this.play(ennemy_play.x, ennemy_play.y);
                    }
                }
                else
                {
                    System.out.println("Position Incorrecte");
                }
            }
        }
        else
        {
            System.out.println("Play out of bound: (" + x + ":" + y + ")");
        }
    }

    private void update_game_state()
    {
        boolean line_found = false;
        for(int j = 0; j < this.play_v.size() && !line_found; j++)
        {
            // vertical : |
            int vertical_line = 1;
            // diagonal 1 : /
            int diagonal_line_1 = 1;
            // horizontal : -
            int horizontal_line = 1;
            // diagonal 2 : \
            int diagonal_line_2 = 1;
            for(int i = 1; i < this.nb_align; i++)
            {
                int jx = this.play_v.get(j).x;
                int jy = this.play_v.get(j).y;
                if((jy - i) >= 0)
                {
                    // Vertical Up
                    if(this.grid[jx][jy] == this.grid[jx][jy - i])
                    {
                        vertical_line++;
                    }
                }
                if((jy + i) < this.nb_rows)
                {
                    // Vertical Down
                    if(this.grid[jx][jy] == this.grid[jx][jy + i])
                    {
                        vertical_line++;
                    }
                }
                // If Not off to the left side
                if((jx - i) >= 0)
                {
                    // Horizontal Left
                    if(this.grid[jx][jy] == this.grid[jx - i][jy])
                    {
                        horizontal_line++;
                    }
                    // If Not off to the top side
                    if((jy - i) >= 0)
                    {
                        // Diagonal 2 Top Left
                        if(this.grid[jx][jy] == this.grid[jx - i][jy - i])
                        {
                            diagonal_line_2++;
                        }
                    }
                    // If Not off to the bottom side
                    if((jy + i) < this.nb_rows)
                    {
                        // Diagonal 2 Bottom Left
                        if(this.grid[jx][jy] == this.grid[jx - i][jy + i])
                        {
                            diagonal_line_1++;
                        }
                    }
                }
                // If not off to the Right Side
                if((jx + i) < this.nb_collumns)
                {
                    // Horizontal Right
                    if(this.grid[jx][jy] == this.grid[jx + i][jy])
                    {
                        horizontal_line++;
                    }
                    // If Not off to the top side
                    if((jy - i) >= 0)
                    {
                        // Diagonal 1 Top Right
                        if(this.grid[jx][jy] == this.grid[jx + i][jy - i])
                        {
                            diagonal_line_1++;
                        }
                    }
                    // If Not off to the bottom side
                    if((jy + i) < this.nb_rows)
                    {
                        // Diagonal 1 Bottom Right
                        if(this.grid[jx][jy] == this.grid[jx + i][jy + i])
                        {
                            diagonal_line_2++;
                        }
                    }
                }
                if(horizontal_line >= this.nb_align)
                {
                    line_found = true;
                }
                if(vertical_line >= this.nb_align)
                {
                    line_found = true;
                }
                if(diagonal_line_1 >= this.nb_align)
                {
                    line_found = true;
                }
                if(diagonal_line_2 >= this.nb_align)
                {
                    line_found = true;
                }
            }
            if(line_found)
            {
                this.player_won = this.grid[this.play_v.get(j).x][this.play_v.get(j).y];
            }
        }
    }
}
