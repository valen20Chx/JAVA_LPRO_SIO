// package src.client.drawing;

import java.util.Vector;
import java.awt.Point;

public abstract class Brush {

    Vector<Point> points1_v;
    Vector<Point> points2_v;

    public Brush() {
        this.points1_v = new Vector<Point>();
        this.points2_v = new Vector<Point>();
    }

    public Vector<Point> getPoints(int pos) {
        Vector<Point> rVector;
        switch (pos) {
            case 1:
                rVector = this.points1_v;
                break;
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