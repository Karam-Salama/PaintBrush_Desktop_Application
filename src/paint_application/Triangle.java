package paint_application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Triangle extends Shape {
    private Point secondtPoint;
    private Point thirdPoint;

    public Triangle(Point startPoint,Point secondtPoint,Point thirdPoint, Color color, boolean isDotted, boolean isFilled) {
        super(startPoint, color, isDotted, isFilled);
        this.secondtPoint = secondtPoint;
        this.thirdPoint = thirdPoint;
    }
    
    // Getters and setters
    public Point getSecondtPoint() {
        return secondtPoint;
    }

    public void setSecondtPoint(Point secondtPoint) {
        this.secondtPoint = secondtPoint;
    }

    public Point getThirdPoint() {
        return thirdPoint;
    }

    public void setThirdPoint(Point thirdPoint) {
        this.thirdPoint = thirdPoint;
    }
    
    // Override abstract method in my parent
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getColor());

        // Set stroke for dotted or solid lines
        if (isDotted()) {
            float[] dashPattern = {5, 5};
            g2d.setStroke(new java.awt.BasicStroke(2, java.awt.BasicStroke.CAP_BUTT, 
                                                   java.awt.BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        } else {
            g2d.setStroke(new java.awt.BasicStroke(2));
        }

        int[] xPoints = {getStartPoint().getX(), secondtPoint.getX(), thirdPoint.getX()};
        int[] yPoints = {getStartPoint().getY(), secondtPoint.getY(), thirdPoint.getY()};

        if (isFilled()) {
            g2d.fillPolygon(xPoints, yPoints, 3);
        } else {
            g2d.drawPolygon(xPoints, yPoints, 3);
        }
    }
    
}
