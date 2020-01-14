import java.io.Serializable;

public class Point implements Serializable {

    private static final long serialVersionUID = 1L;
    private int x;
    private int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void print() {
        System.out.println("(" + this.x + " : " + this.y +")");
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}