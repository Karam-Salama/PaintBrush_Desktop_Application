package paint_application;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Oval extends Shape {
    private int width;
    private int height;

    public Oval(Point startPoint, int width, int height, Color color, boolean isDotted, boolean isFilled) {
        super(startPoint, color, isDotted, isFilled);
        this.width = width;
        this.height = height;
    }
    
    // Getters and setters
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    // Override abstract method in my parent
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getColor());

        if (isDotted()) {
            float[] dashPattern = {5, 5};
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        } else {
            g2d.setStroke(new BasicStroke(2));
        }

        if (isFilled()) {
            g2d.fillOval(getStartPoint().getX(), getStartPoint().getY(), width, height);
        } else {
            g2d.drawOval(getStartPoint().getX(), getStartPoint().getY(), width, height);
        }
    }

    
}
