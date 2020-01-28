import java.util.Vector;
import java.awt.Point;

public class BotEasy {
    private int cols;
    private int lines;
    private Vector<Point> orderPlays;
    private int id;

    BotEasy(int id, int cols, int lines)
    {
        this.id = id;
        this.cols = cols;
        this.lines = lines;
        this.orderPlays = new Vector<Point>(0);
        
        // Ordre
        this.orderPlays.add(new Point(1, 1));
        this.orderPlays.add(new Point(0, 0));
        this.orderPlays.add(new Point(2, 0));
        this.orderPlays.add(new Point(2, 2));
        this.orderPlays.add(new Point(0, 2));
        this.orderPlays.add(new Point(1, 0));
        this.orderPlays.add(new Point(2, 1));
        this.orderPlays.add(new Point(1, 2));
        this.orderPlays.add(new Point(0, 1));
    }

    public Point play(int[][] grid)
    {
        Point play = new Point(0,0);
        boolean exit_loop  = false;
        for(int i = 0; (i < this.orderPlays.size()) && !exit_loop; i++)
        {
            if(grid[this.orderPlays.get(i).x][this.orderPlays.get(i).y] == 0)
            {
                play = new Point(this.orderPlays.get(i).x, this.orderPlays.get(i).y);
                exit_loop = true;
            }
        }
        return play;
    }
}
