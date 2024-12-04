package paint_application;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pencil extends Shape {
    private List<Point> points;
    private int strokeWidth;

    public Pencil(Point startPoint, Color color) {
        super(startPoint, color, false, false);
        points = new ArrayList<>();
        points.add(startPoint); // Initialize with the starting point
        this.strokeWidth = 2;  // Default stroke width 
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth; 
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void addPoint(Point point) {
        if (point != null) { // Validate point
            points.add(point);
        }
    }

    @Override
    public void draw(Graphics g) {
        if (points.size() < 2) {
            return; // Nothing to draw if there are less than two points
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getColor());
        g2d.setStroke(new BasicStroke(strokeWidth));

        // Draw lines between consecutive points
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }
}
