import java.util.Random;
import java.awt.Point;

public class Game {
    private int nb_cols;
    private int nb_rows;
    private int end_val;
    private Square[][] grid;
    private int nb_plays;
    private boolean game_over;
    private Bot ennemy;
    private int player_id;
    private int turn;
    private int uncoveredSquares;

    Game(int nb_cols, int nb_rows)
    {
        this.nb_cols = nb_cols;
        this.nb_rows = nb_rows;
        this.end_val = (this.nb_cols * this.nb_rows);
        this.grid = new Square[this.nb_cols][this.nb_rows];
        this.nb_plays = 0;
        this.game_over = false;
        this.ennemy = new Bot(2, this.nb_cols, this.nb_rows);
        this.player_id = 1;
        this.turn = 1;
        this.uncoveredSquares = 0;

        for(int x = 0; x < this.nb_cols; x++)
        {
            for(int y = 0; y < this.nb_rows; y++)
            {
                this.grid[x][y] = null;
            }
        }

        // Remplissage
        Random rand = new Random();
        for(int i = 0; i < this.nb_cols; i++)
        {
            for(int j = 0; j < this.nb_rows; j++)
            {
                int a;
                do {
                    a = rand.nextInt(this.end_val) + 1;
                } while(this.trouve(a));
                this.grid[i][j] = new Square(a);
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
        else if(this.uncoveredSquares >= (this.nb_cols * this.nb_rows))
        {
            System.out.println("All squares were played.");
        }
        else if(this.grid[x][y].is_hidden())
        {
            this.uncoveredSquares++;
            this.grid[x][y].discover();
            this.checkGame(x, y);

            if(this.turn == this.player_id)
            {
                this.nb_plays++;
                this.turn = this.ennemy.get_id();
                Point tempPlay = this.ennemy.play(this.grid);
                this.play(tempPlay.x, tempPlay.y);
            }
            else if(this.turn == this.ennemy.get_id())
            {
                this.turn = this.player_id;
            }
        }
        else
        {
            System.out.println("Error: Square already discovered");
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

    public int get_turn()
    {
        return this.turn;
    }
    
    public String get_winner()
    {
        return (this.turn == 1 ? "Bot" : "Player");
    }

    public int get_bot_nb_plays()
    {
        return this.ennemy.get_nb_plays();
    }
}