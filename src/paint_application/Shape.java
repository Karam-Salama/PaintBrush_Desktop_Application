
package paint_application;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
    private Point startPoint;
    private Color color;
    private boolean isDotted;
    private boolean isFilled;

    public Shape(Point startPoint, Color color, boolean isDotted, boolean isFilled) {
        this.startPoint = startPoint;
        this.color = color;
        this.isDotted = isDotted;
        this.isFilled = isFilled;
    }

    // Getters and setters
    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isDotted() {
        return isDotted;
    }

    public void setDotted(boolean isDotted) {
        this.isDotted = isDotted;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }
    
    
    // Abstract method for drawing shapes
    public abstract void draw(Graphics g);

}
