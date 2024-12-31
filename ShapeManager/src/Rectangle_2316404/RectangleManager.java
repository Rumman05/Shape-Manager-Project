package Rectangle_2316404;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class RectangleManager {
    private ArrayList<RectangleShape> rectangleList = new ArrayList<>(); // List to store all RectangleShape objects
    private RectangleShape currentRectangle; // Stores the currently selected RectangleShape object

    // Adds a new rectangle to the list if it has a valid ID (i.e., the ID is not already taken)
    public boolean addRectangle(RectangleShape rectangle) {
        if (!validateId(rectangle.getId())) { // Check if the ID is unique
            return false; // Return false if the ID is not valid
        }
        rectangleList.add(rectangle); // Add the rectangle to the list
        return true; // Return true if the rectangle was successfully added
    }

    // Searches for a rectangle by its ID and sets it as the current rectangle if found
    public RectangleShape searchRectangle(int id) {
        for (RectangleShape rectangle : rectangleList) { // Loop through all rectangles in the list
            if (rectangle.getId() == id) { // Check if the current rectangle's ID matches the search ID
                currentRectangle = rectangle; // Set the found rectangle as the current rectangle
                return rectangle; // Return the found rectangle
            }
        }
        return null; // Return null if no rectangle with the given ID is found
    }

    // Returns a sorted list of rectangles based on their ID (ascending order)
    public ArrayList<RectangleShape> getSortedRectangles() {
        ArrayList<RectangleShape> sortedList = new ArrayList<>(rectangleList); // Copy the list to avoid modifying the original
        Collections.sort(sortedList); // Sort the list based on the natural ordering of RectangleShape (which assumes the ID is comparable)
        return sortedList; // Return the sorted list of rectangles
    }

    // Updates an existing rectangle's properties (width, height, position, color) based on its ID
    public boolean updateRectangle(int id, int width, int height, int xPos, int yPos, Color color) {
        RectangleShape rectangle = searchRectangle(id); // Search for the rectangle by ID
        if (rectangle != null) { // If the rectangle is found
            rectangle.setWidth(width); // Set the new width
            rectangle.setHeight(height); // Set the new height
            rectangle.setXpos(xPos); // Set the new X position
            rectangle.setYpos(yPos); // Set the new Y position
            rectangle.setColor(color); // Set the new color
            return true; // Return true indicating the update was successful
        }
        return false; // Return false if no rectangle with the given ID was found
    }

    // Validates that the rectangle ID is unique (not already used by another rectangle)
    public boolean validateId(int id) {
        for (RectangleShape rectangle : rectangleList) { // Loop through all rectangles in the list
            if (rectangle.getId() == id) { // Check if the rectangle ID already exists in the list
                return false; // Return false if the ID is already taken
            }
        }
        return true; // Return true if the ID is unique
    }

    // Returns the list of all rectangles
    public ArrayList<RectangleShape> getRectangles() {
        return rectangleList; // Return the list of all rectangles
    }

    // Returns the current rectangle that is selected or being viewed
    public RectangleShape getCurrentRectangle() {
        return currentRectangle; // Return the current selected rectangle
    }
}
