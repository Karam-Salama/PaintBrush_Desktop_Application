package paint_application;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Eraser extends Shape {
    private List<Point> points;
    private int sizeToErase;

    public Eraser(Point startPoint,int sizeToErase, Color color) {
        super(startPoint, color, false, false);
        this.sizeToErase = sizeToErase;
        points = new ArrayList<>();
        points.add(startPoint); // Initialize with the starting point
    }

    public void addPoint(Point point) {
        if (point != null) { // Validate point
            points.add(point);
        }
    }

    public int getSizeToErase() {
        return sizeToErase;
    }

    public void setSizeToErase(int sizeToErase) {
        this.sizeToErase = sizeToErase;
    }

    

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor()); 
        for (Point point : points) {
            g.fillOval(point.getX() - sizeToErase / 2, point.getY() - sizeToErase / 2, sizeToErase, sizeToErase);
        }
    }
}
