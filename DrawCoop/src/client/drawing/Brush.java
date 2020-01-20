package client.drawing;

import java.util.Vector;
import java.awt.Point;

public abstract class Brush {

    Vector<Point> points1_v;
    Vector<Point> points2_v;

    public Brush() {
        this.points1_v = null;
        this.points2_v = null;
    }

    public Vector<Point> getPoints(int pos) {
        Vector<Point> rVector;
        switch (pos) {
            case 1:
                rVector = this.points1_v;
            case 2:
                rVector = this.points2_v;
                break;
            default:
                rVector = null;
                break;
        }

        return rVector;
    }
}