// package src.client.drawing;

import java.awt.Point;

public class PencilBrush extends Brush {
    public PencilBrush(Point point1, Point point2) {
        super();
        this.points1_v.add(point1);
        this.points2_v.add(point2);
    }
}