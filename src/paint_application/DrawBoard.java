package paint_application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

// Enum to represent available shapes
enum ShapeType {
    Line, Rectangle, Oval, Triangle, Pencil, Eraser
}

public class DrawBoard extends JPanel {

    // List to store all drawn shapes
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private ShapeType selectedShape = ShapeType.Pencil; // Default selected shape
    private Color color = Color.BLACK; // Default color
    private boolean isDefault = true;
    private boolean isDotted = false; // Default dotted style
    private boolean isFilled = false; // Default filled style
    private Point startPoint; // Starting point for drawing
    private Pencil pen; // For freehand drawing with Pencil

    public DrawBoard() {
        this.setBackground(Color.WHITE);

        // Add mouse listeners for drawing
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Initialize startPoint on mouse press
                startPoint = new Point(e.getX(), e.getY());

                // Create the selected shape
                switch (selectedShape) {
                    case Line:
                        shapes.add(new Line(startPoint, 0, 0, color, isDotted));
                        break;
                    case Rectangle:
                        shapes.add(new Rectangle(startPoint, 0, 0, color, isDotted, isFilled));
                        break;
                    case Oval:
                        shapes.add(new Oval(startPoint, 0, 0, color, isDotted, isFilled));
                        break;
                    case Triangle:
                        shapes.add(new Triangle(startPoint, startPoint, startPoint, color, isDotted, isFilled));
                        break;
                    case Pencil:
                        pen = new Pencil(startPoint, color);
                        shapes.add(pen);
                        break;
                    case Eraser:
                        shapes.add(new Eraser(startPoint, 20, getBackground())); // Default eraser size
                        break;
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                // Update the shape being drawn
                if (!shapes.isEmpty()) {
                    Shape currentShape = shapes.get(shapes.size() - 1);

                    if (currentShape instanceof Line) {
                        updateLine((Line) currentShape, x, y);
                    } else if (currentShape instanceof Rectangle) {
                        updateRectangle((Rectangle) currentShape, x, y);
                    } else if (currentShape instanceof Oval) {
                        updateOval((Oval) currentShape, x, y);
                    } else if (currentShape instanceof Triangle) {
                        updateTriangle((Triangle) currentShape, x, y);
                    } else if (currentShape instanceof Pencil) {
                        ((Pencil) currentShape).addPoint(new Point(x, y));
                    } else if (currentShape instanceof Eraser) {
                        ((Eraser) currentShape).addPoint(new Point(x, y));
                    }
                }
                repaint();
            }
        });
    }
    
    

    // Update methods for each shape
    private void updateLine(Line line, int x, int y) {
        line.setWidth(x - line.getStartPoint().getX());
        line.setHeight(y - line.getStartPoint().getY());
    }

    private void updateRectangle(Rectangle rect, int x, int y) {
        int width = Math.abs(x - startPoint.getX());
        int height = Math.abs(y - startPoint.getY());
        rect.setStartPoint(new Point(Math.min(startPoint.getX(), x), Math.min(startPoint.getY(), y)));
        rect.setWidth(width);
        rect.setHeight(height);
    }

    private void updateOval(Oval oval, int x, int y) {
        int width = Math.abs(x - startPoint.getX());
        int height = Math.abs(y - startPoint.getY());
        oval.setStartPoint(new Point(Math.min(startPoint.getX(), x), Math.min(startPoint.getY(), y)));
        oval.setWidth(width);
        oval.setHeight(height);
    }

    private void updateTriangle(Triangle triangle, int x, int y) {
        triangle.setSecondtPoint(new Point(x, y));
        int x3 = startPoint.getX() + (x - startPoint.getX());
        int y3 = startPoint.getY();
        triangle.setStartPoint(new Point(x3, y3));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw all shapes in the shapes list
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    // Methods to update properties
    public void setSelectedShape(ShapeType selectedShape) {
        this.selectedShape = selectedShape;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDotted(boolean isDotted) {
        this.isDotted = isDotted;
    }

    public void setFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }
    
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public void clearAll() {
        shapes.clear();
        repaint();
    }

    public void undo() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }
    }
}
