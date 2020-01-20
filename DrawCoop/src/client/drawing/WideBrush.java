package src.client.drawing;

import java.awt.Point;

public class WideBrush extends Brush {
    public WideBrush(Point point1, Point point2, int width) {
        for (int i = 0; i < width; i++) {
            Point coefDir = new Point(Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));
            Point normalCoefDir = new Point((coefDir.x == 0 ? 0 : coefDir.y / coefDir.x), (coefDir.y == 0 ? 0 : coefDir.x / coefDir.y));
            Point tempPoint1 = new Point(point1.x + (i + (normalCoefDir.x)), point1.y + (i + (normalCoefDir.y)));
            Point tempPoint2 = new Point(point2.x + (i + (normalCoefDir.x)), point2.y + (i + (normalCoefDir.y)));
            this.points1_v.add(tempPoint1);
            this.points2_v.add(tempPoint2);
        }
    }
}