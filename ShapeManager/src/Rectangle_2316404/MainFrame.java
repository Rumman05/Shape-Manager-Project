package Rectangle_2316404;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // Declare input fields and other UI components
    private JTextField idField, widthField, heightField, xPosField, yPosField, colorField; // Text fields for rectangle attributes
    private JTextArea consoleOutput; // Text area for displaying program output
    private JPanel displayPanel; // Panel to graphically display the rectangle
    private RectangleManager rectangleManager = new RectangleManager(); // Object to manage rectangle data

    public MainFrame() {
        // Set up the main frame
        setTitle("Shape Manager"); // Sets the title of the application window
        setSize(600, 400); // Sets the initial size of the application window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the window to close when the close button is clicked
        setLayout(new BorderLayout()); // Uses BorderLayout to arrange components

        // Setup different panels for the UI
        setupTopPanel(); // Creates and adds the input panel
        setupCenterPanel(); // Creates and adds the display panel
        setupBottomPanel(); // Creates and adds the button and console panel
    }

    private void setupTopPanel() {
        // Panel for input fields
        JPanel topPanel = new JPanel(new GridLayout(2, 6)); // Creates a panel using a grid layout (2 rows, 6 columns)

        // Add labels and input fields for the user to fill
        topPanel.add(new JLabel("ID (231 ___):")); // Adds a label for the ID field
        idField = new JTextField(); // Creates a text field for the ID
        topPanel.add(idField); // Adds the ID text field to the panel

        topPanel.add(new JLabel("Width:")); // Adds a label for the width field
        widthField = new JTextField(); // Creates a text field for the width
        topPanel.add(widthField); // Adds the width text field to the panel

        topPanel.add(new JLabel("Height:")); // Adds a label for the height field
        heightField = new JTextField(); // Creates a text field for the height
        topPanel.add(heightField); // Adds the height text field to the panel

        topPanel.add(new JLabel("X Position:")); // Adds a label for the x-position field
        xPosField = new JTextField(); // Creates a text field for the x-position
        topPanel.add(xPosField); // Adds the x-position text field to the panel

        topPanel.add(new JLabel("Y Position:")); // Adds a label for the y-position field
        yPosField = new JTextField(); // Creates a text field for the y-position
        topPanel.add(yPosField); // Adds the y-position text field to the panel

        topPanel.add(new JLabel("Colour (R,G,B):")); // Adds a label for the color field
        colorField = new JTextField(); // Creates a text field for the color
        topPanel.add(colorField); // Adds the color text field to the panel

        // Add the topPanel to the frame
        add(topPanel, BorderLayout.NORTH); // Adds the input panel to the north region of the frame
    }

    private void setupCenterPanel() {
        // Panel for displaying rectangles
        displayPanel = new JPanel() { // Creates a custom JPanel to handle painting
            @Override
            protected void paintComponent(Graphics g) { // Overrides the paintComponent method to draw the rectangle
                super.paintComponent(g); // Calls the superclass's paintComponent method
                if (rectangleManager.getCurrentRectangle() != null) {
                    rectangleManager.getCurrentRectangle().draw(g); // Draws the rectangle using the RectangleShape's draw method
                }
            }
        };
        displayPanel.setBackground(Color.WHITE); // Sets the background color of the display panel to white
        add(displayPanel, BorderLayout.CENTER); // Adds the display panel to the center region of the frame
    }

    private void setupBottomPanel() {
        //Panel for buttons and console output
        JPanel bottomPanel = new JPanel(); // Creates a panel for the buttons

        // Add buttons with their respective actions
        JButton addButton = new JButton("Add"); // Creates a button to add a rectangle
        addButton.addActionListener(e -> addRectangle()); // Adds an action listener to the add button
        bottomPanel.add(addButton); // Adds the add button to the panel

        JButton searchButton = new JButton("Search"); // Creates a button to search for a rectangle
        searchButton.addActionListener(e -> searchRectangle()); // Adds an action listener to the search button
        bottomPanel.add(searchButton); // Adds the search button to the panel

        JButton displayButton = new JButton("Display All"); // Creates a button to display all rectangles
        displayButton.addActionListener(e -> displayAllRectangles()); // Adds an action listener to the display all button
        bottomPanel.add(displayButton); // Adds the display all button to the panel

        JButton sortButton = new JButton("Sort"); // Creates a button to sort rectangles
        sortButton.addActionListener(e -> sortRectangles()); // Adds an action listener to the sort button
        bottomPanel.add(sortButton); // Adds the sort button to the panel

        JButton updateButton = new JButton("Update"); // Creates a button to update a rectangle
        updateButton.addActionListener(e -> updateRectangle()); // Adds an action listener to the update button
        bottomPanel.add(updateButton); // Adds the update button to the panel

        // Set up the console output area
        consoleOutput = new JTextArea(5, 50); // Creates a text area for console output
        consoleOutput.setEditable(false); // Makes the console output text area non-editable
        consoleOutput.setBackground(new Color(200, 200, 200)); // Sets the background color of the console output
        Font newFont = new Font("Trebuchet MS", Font.PLAIN, 14); // Sets the font for the console output
        consoleOutput.setFont(newFont); // Applies the font to the console output
        JScrollPane scrollPane = new JScrollPane(consoleOutput); // Creates a scroll pane for the console output

        // Add the bottom panel and console output to the frame
        add(bottomPanel, BorderLayout.SOUTH); // Adds the button panel to the south region of the frame
        add(scrollPane, BorderLayout.EAST); // Adds the console output scroll pane to the east region of the frame
    }


    private void addRectangle() {
        try {
            //Basic input validation
            StringBuilder errorMessage = new StringBuilder();
            if (idField.getText().isEmpty()) errorMessage.append("ID is required.\n");
            if (widthField.getText().isEmpty()) errorMessage.append("Width is required.\n");
            if (heightField.getText().isEmpty()) errorMessage.append("Height is required.\n");
            if (xPosField.getText().isEmpty()) errorMessage.append("X Position is required.\n");
            if (yPosField.getText().isEmpty()) errorMessage.append("Y Position is required.\n");
            if (colorField.getText().isEmpty()) errorMessage.append("Colour is required.\n");

            if (errorMessage.length() > 0) {
                consoleOutput.append(errorMessage.toString());
                clearFields();
                return;
            }

            String idText = idField.getText().trim();
            if (!idText.startsWith("231 ")) {
                consoleOutput.append("ID must start with '231 ' followed by a number up to 250.\n");
                clearFields();
                return;
            }

            int id = Integer.parseInt(idText.substring(4).trim());
            if (id < 1 || id > 250) {
                consoleOutput.append("ID must be between 231 001 and 231 250.\n");
                clearFields();
                return;
            }

            int width = Integer.parseInt(widthField.getText());
            int height = Integer.parseInt(heightField.getText());

            //Validation based on ID parity
            if (id % 10 % 2 == 0 && height % 2 == 0) {
                consoleOutput.append("ID ends with an even number, so height must be odd.\n");
                clearFields();
                return;
            }

            if (id % 10 % 2 != 0 && width % 2 != 0) {
                consoleOutput.append("ID ends with an odd number, so width must be even.\n");
                clearFields();
                return;
            }

            if (width < 1 || height < 1) {
                consoleOutput.append("Width and height cannot be below 1.\n");
                clearFields();
                return;
            }

            if (width > 1500 || height > 1500) {
                consoleOutput.append("Width and height must not exceed 1500.\n");
                clearFields();
                return;
            }

            if (width == height) {
                consoleOutput.append("Width and height cannot be equal. This is a square, not a rectangle.\n");
                clearFields();
                return;
            }

            int xPos = Integer.parseInt(xPosField.getText());
            int yPos = Integer.parseInt(yPosField.getText());

            if (xPos < 0 || yPos < 0) {
                consoleOutput.append("X and Y positions cannot be less than 0.\n");
                clearFields();
                return;
            }

            int panelWidth = displayPanel.getWidth();
            int panelHeight = displayPanel.getHeight();

            //Further validation to ensure rectangle fits within the panel
            if (width > panelWidth || height > panelHeight) {
                consoleOutput.append("Rectangle is too large to fit in the display area.\n");
                clearFields();
                return;
            }

            if (xPos + width > panelWidth || yPos + height > panelHeight) {
                consoleOutput.append("Rectangle position and size cause it to exceed display panel boundaries.\n");
                clearFields();
                return;
            }

            String[] colorValues = colorField.getText().split(",");
            if (colorValues.length != 3) {
                consoleOutput.append("Invalid color format. Enter color as R,G,B (e.g., 255,0,0 for red).\n");
                clearFields();
                return;
            }

            int r = Integer.parseInt(colorValues[0]);
            int g = Integer.parseInt(colorValues[1]);
            int b = Integer.parseInt(colorValues[2]);

            if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                consoleOutput.append("Invalid color values. Each value must be between 0 and 255.\n");
                clearFields();
                return;
            }

            Color color = new Color(r, g, b);
            RectangleShape rectangle = new RectangleShape(Integer.parseInt(idText.replace("231 ", "")), width, height, xPos, yPos, color);

            if (rectangleManager.addRectangle(rectangle)) {
                consoleOutput.append("Rectangle added successfully.\n");
                displayPanel.repaint();
            } else {
                consoleOutput.append("Rectangle with ID already exists.\n");
            }

        } catch (NumberFormatException e) {
            consoleOutput.append("Invalid input. Please enter numeric values for width, height, x, y, and color components.\n");
        } catch (Exception e) {  // Catching other potential exceptions during rectangle creation.
            consoleOutput.append("An error occurred while adding the rectangle: " + e.getMessage() + "\n");
        }
        clearFields();
    }

    private void searchRectangle() {
        // Search for a rectangle by ID
        String input = JOptionPane.showInputDialog(this, "Enter the ID of the rectangle to search for (e.g., 231 001):", "Search Rectangle", JOptionPane.PLAIN_MESSAGE);
        if (input != null && !input.isEmpty()) {
            try {
                if (!input.startsWith("231 ")) {
                    JOptionPane.showMessageDialog(this, "ID must look like e.g. '231 001'", "Invalid ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(input.substring(4).trim());

                if (id < 1 || id > 250) {
                    JOptionPane.showMessageDialog(this, "ID must be between 231 001 and 231 250.", "Invalid ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Search for the rectangle in the manager
                RectangleShape rectangle = rectangleManager.searchRectangle(id);
                if (rectangle != null) {
                    consoleOutput.append("Rectangle found: ID " + input + "\n");
                    displayPanel.repaint(); // Repaint to reflect any changes
                } else {
                    consoleOutput.append("Rectangle not found.\n");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid ID in the format '231 ___'.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
        clearFields();
    }

    private void displayAllRectangles() {
        // Display all rectangles in the console output
        consoleOutput.setText(""); // Clears the console output area
        java.util.List<RectangleShape> rectangles = rectangleManager.getRectangles(); // Gets all rectangles from the manager

        if (rectangles.isEmpty()) {
            consoleOutput.append("No rectangles to display.\n");
            return;
        }

        consoleOutput.append("Displaying all rectangles:\n");
        for (RectangleShape rectangle : rectangles) { // Iterates through each rectangle
            String colorString = String.format("(%d, %d, %d)", // Formats the color string
                    rectangle.getColor().getRed(),
                    rectangle.getColor().getGreen(),
                    rectangle.getColor().getBlue());

            consoleOutput.append(String.format( // Formats and appends the rectangle information to the console
                    "ID: 231 %03d | Size: %dx%d | Position: (%d, %d) | Colour: %s\n",
                    rectangle.getId(),
                    rectangle.getWidth(),
                    rectangle.getHeight(),
                    rectangle.getXPos(),
                    rectangle.getYPos(),
                    colorString
            ));
        }
    }

    private void sortRectangles() {
        // Sort and display all rectangles
        consoleOutput.setText(""); // Clears the console output area
        java.util.List<RectangleShape> sortedRectangles = rectangleManager.getSortedRectangles(); // Gets the sorted list of rectangles

        if (sortedRectangles.isEmpty()) {
            consoleOutput.append("No rectangles to sort.\n");
            return;
        }

        consoleOutput.append("Displaying all rectangles (sorted by ID):\n");
        for (RectangleShape rectangle : sortedRectangles) { // Iterates through each sorted rectangle
            String colorString = String.format("(%d, %d, %d)",
                    rectangle.getColor().getRed(),
                    rectangle.getColor().getGreen(),
                    rectangle.getColor().getBlue());

            consoleOutput.append(String.format(
                    "ID: 231 %03d | Size: %dx%d| Position: (%d, %d) | Colour: %s\n",
                    rectangle.getId(),
                    rectangle.getWidth(),
                    rectangle.getHeight(),
                    rectangle.getXPos(),
                    rectangle.getYPos(),
                    colorString
            ));
        }
    }

    private void updateRectangle() {
        // Update an existing rectangle by ID
        String input = JOptionPane.showInputDialog(this, "Enter the ID of the rectangle to update (e.g., 231 001):", "Update Rectangle", JOptionPane.PLAIN_MESSAGE);
        if (input != null && !input.isEmpty()) {
            try {
                if (!input.startsWith("231 ")) {
                    JOptionPane.showMessageDialog(this, "ID must start with '231 ' followed by a number.", "Invalid ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(input.substring(4).trim());

                if (id < 1 || id > 250) {
                    JOptionPane.showMessageDialog(this, "ID must be between 231 001 and 231 250.", "Invalid ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Input validation for update
                if (widthField.getText().isEmpty() || heightField.getText().isEmpty() || xPosField.getText().isEmpty() || yPosField.getText().isEmpty() || colorField.getText().isEmpty()) {
                    consoleOutput.append("All fields must be filled.\n");
                    return;
                }

                int width = Integer.parseInt(widthField.getText());
                int height = Integer.parseInt(heightField.getText());

                if (width < 1 || height < 1) {
                    consoleOutput.append("Width and height cannot be below 1.\n");
                    return;
                }

                if (width > 1500 || height > 1500) {
                    consoleOutput.append("Width and height must not exceed 1500.\n");
                    return;
                }

                if (width == height) {
                    consoleOutput.append("Width and height cannot be equal. This is a square, not a rectangle.\n");
                    return;
                }

                int xPos = Integer.parseInt(xPosField.getText());
                int yPos = Integer.parseInt(yPosField.getText());

                if (xPos < 0 || yPos < 0) {
                    consoleOutput.append("X and Y positions cannot be less than 0.\n");
                    return;
                }

                String[] colorValues = colorField.getText().split(",");
                if (colorValues.length != 3) {
                    consoleOutput.append("Invalid color format. Enter color as R,G,B (e.g., 255,0,0 for red).\n");
                    return;
                }

                int r = Integer.parseInt(colorValues[0]);
                int g = Integer.parseInt(colorValues[1]);
                int b = Integer.parseInt(colorValues[2]);

                if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                    consoleOutput.append("Invalid color values. Each value must be between 0 and 255.\n");
                    return;
                }

                Color color = new Color(r, g, b);

                if (rectangleManager.updateRectangle(id, width, height, xPos, yPos, color)) {
                    consoleOutput.append("Rectangle updated successfully.\n");
                    displayPanel.repaint(); // Repaint to show updated rectangle
                } else {
                    consoleOutput.append("Rectangle with ID " + input + " not found.\n");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
        clearFields();
    }

    private void clearFields() {
        // Clear all the input fields
        idField.setText(""); // Clears the text in the ID field
        widthField.setText(""); // Clears the text in the width field
        heightField.setText(""); // Clears the text in the height field
        xPosField.setText(""); // Clears the text in the x-position field
        yPosField.setText(""); // Clears the text in the y-position field
        colorField.setText(""); // Clears the text in the color field
    }


    public static void main(String[] args) {
        // Start the application
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true)); // Starts the GUI on the Event Dispatch Thread
    }
}