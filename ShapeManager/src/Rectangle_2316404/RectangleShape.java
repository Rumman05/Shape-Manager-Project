package Rectangle_2316404;

import java.awt.Color;

public class RectangleShape implements Comparable<RectangleShape> {
    private Color color; // Color of the rectangle
    private int id; // Unique identifier for the rectangle
    private int width; // Width of the rectangle
    private int height; // Height of the rectangle
    private int xPos; // X position (horizontal position) of the rectangle
    private int yPos; // Y position (vertical position) of the rectangle

    // Constructor to initialize a new rectangle with the provided properties
    public RectangleShape(int id, int width, int height, int xPos, int yPos, Color color) {
        this.id = id; // Set the ID of the rectangle
        this.width = width; // Set the width of the rectangle
        this.height = height; // Set the height of the rectangle
        this.xPos = xPos; // Set the X position of the rectangle
        this.yPos = yPos; // Set the Y position of the rectangle
        this.color = color; // Set the color of the rectangle
    }

    // Getter for the rectangle's ID
    public int getId() {
        return id;
    }

    // Getter for the rectangle's width
    public int getWidth() {
        return width;
    }

    // Getter for the rectangle's height
    public int getHeight() {
        return height;
    }

    // Getter for the rectangle's X position
    public int getXPos() {
        return xPos;
    }

    // Getter for the rectangle's Y position
    public int getYPos() {
        return yPos;
    }

    // Getter for the rectangle's color
    public Color getColor() {
        return color;
    }

    // Setter for the rectangle's width
    public void setWidth(int width) {
        this.width = width;
    }

    // Setter for the rectangle's height
    public void setHeight(int height) {
        this.height = height;
    }

    // Setter for the rectangle's color
    public void setColor(Color color) {
        this.color = color;
    }

    // Method to draw the rectangle on the screen using Graphics object
    public void draw(java.awt.Graphics g) {
        g.setColor(color); // Set the drawing color to the rectangle's color
        g.fillRect(xPos, yPos, width, height); // Draw a filled rectangle at the specified position and size
    }

    // Implementation of the compareTo method to compare rectangles by their IDs (used for sorting)
    @Override
    public int compareTo(RectangleShape other) {
        return Integer.compare(this.id, other.id); // Compare IDs to sort rectangles by ID
    }

    // Setter for the rectangle's X position
    public void setXpos(int xPos) {
        this.xPos = xPos;
    }

    // Setter for the rectangle's Y position
    public void setYpos(int yPos) {
        this.yPos = yPos;
    }
}
