import java.util.Random;

public class Game {
    private int nb_cols;
    private int nb_rows;
    private int end_val;
    private Square[][] grid;
    private int nb_plays;
    private boolean game_over;

    Game(int nb_cols, int nb_rows)
    {
        this.nb_cols = nb_cols;
        this.nb_rows = nb_rows;
        this.end_val = (this.nb_cols * this.nb_rows);
        this.grid = new Square[this.nb_cols][this.nb_rows];
        this.nb_plays = 0;
        this.game_over = false;

        for(int x = 0; x < this.nb_cols; x++)
        {
            for(int y = 0; y < this.nb_rows; y++)
            {
                this.grid[x][y] = new Square(false);
            }
        }
    }

    private boolean trouve(int a)
    {
        for (int i = 0; i < this.nb_cols; i++) {
            for (int j = 0; j < this.nb_rows; j++) {
                if(this.grid[i][j] != null)
                {
                    if(this.grid[i][j].get_val() == a)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void play(int x, int y)
    {
        if(game_over)
        {
            System.out.println("Game is Over");
        }
        else if(this.grid[x][y].is_hidden())
        {
            this.grid[x][y].discover();
            this.nb_plays++;
            this.checkGame(x, y);
        }
        else
        {
            System.out.println("Error: Square already discovererd");
        }
    }

    private void checkGame(int x, int y)
    {
        if(this.grid[x][y].get_val() == this.end_val)
        {
            this.game_over = true;
        }
    }

    public int get_nb_plays()
    {
        return this.nb_plays;
    }

    public boolean is_GameOver()
    {
        return this.game_over;
    }

    public Square[][] get_grid()
    {
        return this.grid;
    }
}