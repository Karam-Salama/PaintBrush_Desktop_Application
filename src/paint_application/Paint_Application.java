package paint_application;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paint_Application extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("PaintBrush Desktop Application");
        DrawBoard drawBoard = new DrawBoard();

        // Set the layout for the frame
        frame.setLayout(new BorderLayout());

        // Create the control panel (for buttons and checkboxes)
        JPanel controlPanel = createControlPanel(drawBoard);

        // Add components to the frame
        frame.add(controlPanel, BorderLayout.NORTH); // Control panel at the top
        frame.add(drawBoard, BorderLayout.CENTER);    // Drawing area in the center

        // Set the window properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 650);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Light background
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon("logo.jpg").getImage());
    }

    // Method to create the control panel
    private static JPanel createControlPanel(DrawBoard drawBoard) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(200, 220, 240)); // Light blue background
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margins
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margins

        // Create checkboxes
        JCheckBox filledCheckbox = new JCheckBox("Filled");
        JCheckBox dottedCheckbox = new JCheckBox("Dotted");

        // Add functionality to control visibility based on selections
        filledCheckbox.addItemListener(e -> {
            drawBoard.setFilled(filledCheckbox.isSelected());
            updateVisibility(filledCheckbox, dottedCheckbox);
        });

        dottedCheckbox.addItemListener(e -> {
            drawBoard.setDotted(dottedCheckbox.isSelected());
            updateVisibility(filledCheckbox, dottedCheckbox);
        });

        // Add components to the panel
        panel.add(actionButton("ClearAll", drawBoard));
        panel.add(actionButton("Undo", drawBoard));
        panel.add(shapeButton("Line", ShapeType.Line, drawBoard));
        panel.add(shapeButton("Rectangle", ShapeType.Rectangle, drawBoard));
        panel.add(shapeButton("Oval", ShapeType.Oval, drawBoard));
        panel.add(shapeButton("Triangle", ShapeType.Triangle, drawBoard));
        panel.add(shapeButton("Pencil", ShapeType.Pencil, drawBoard));
        panel.add(shapeButton("Eraser", ShapeType.Eraser, drawBoard));
        panel.add(filledCheckbox);
        panel.add(dottedCheckbox); // Add Dotted to the panel
        panel.add(colorButton("Black", Color.BLACK, drawBoard));
        panel.add(colorButton("Red", Color.RED, drawBoard));
        panel.add(colorButton("Green", Color.GREEN, drawBoard));
        panel.add(colorButton("Blue", Color.BLUE, drawBoard));

        return panel;
    }

    // Method to update visibility of checkboxes
    private static void updateVisibility(JCheckBox filled, JCheckBox dotted) {
        if (filled.isSelected()) {
            dotted.setVisible(false);
        } else if (dotted.isSelected()) {
            filled.setVisible(false);
        } else {
            // If neither is selected, both are visible
            filled.setVisible(true);
            dotted.setVisible(true);
        }
    }

    // Helper method to create color buttons
    private static JButton colorButton(String text, Color color, DrawBoard drawBoard) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.addActionListener(e -> drawBoard.setColor(color));
        return button;
    }

    // Helper method to create shape buttons
    private static JButton shapeButton(String text, ShapeType shape, DrawBoard drawBoard) {
        JButton button = new JButton(text);
        button.addActionListener(e -> drawBoard.setSelectedShape(shape));
        return button;
    }

    // Helper method to create action buttons
    private static JButton actionButton(String text, DrawBoard painter) {
        JButton button = new JButton(text);
        button.addActionListener(e -> {
            if (text.equals("ClearAll")) {
                painter.clearAll();
            } else if (text.equals("Undo")) {
                painter.undo();
            }
        });
        return button;
    }
}
