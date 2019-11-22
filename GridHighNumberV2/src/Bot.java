import java.awt.Point;
import java.util.Random;

public class Bot {
    private int id;
    private int cols;
    private int rows;
    private int nb_plays;
    private int score;

    Bot(int id, int cols, int rows)
    {
        this.id = id;
        this.cols = cols;
        this.rows = rows;
        this.nb_plays = 0;
        this.score = 0;
    }

    public int get_id()
    {
        return this.id;
    }

    public Point play(Square[][] grid)
    {
        Point playPoint = new Point();
        Random rand = new Random();
        do {
            playPoint.setLocation(rand.nextInt(this.cols), rand.nextInt(this.rows));
        } while(!grid[playPoint.x][playPoint.y].is_hidden());

        this.nb_plays++;
        
        return playPoint;
    }

    public int get_nb_plays()
    {
        return this.nb_plays;
    }
}