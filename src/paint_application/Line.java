
package paint_application;
import java.awt.*;

public class Line extends Shape {
    private int width;
    private int height;
    
    public Line(Point startPoint ,int width, int height,Color color, boolean isDotted) {
        super(startPoint, color, isDotted, false);
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
            float[] dashPattern = {4, 4};
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        } else {
            g2d.setStroke(new BasicStroke(2));
        }
        g2d.drawLine(getStartPoint().getX(), getStartPoint().getY(), getStartPoint().getX() + width, getStartPoint().getY()+ height);
    }

   
}
