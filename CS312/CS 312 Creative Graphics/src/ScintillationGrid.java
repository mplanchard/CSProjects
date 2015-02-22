import java.awt.Color;
import java.awt.Graphics;

/**
 * @author  Matthew S. Planchard
 * @version 22 September 2014
 * CS312 Assignment 3.
 * 
 * On my honor, Matthew S. Planchard, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to print out various scintillation grids and a student designed drawing. 
 *
 *  email address: msplanchard@gmail.com
 *  UTEID: msp2377
 *  Unique 5 digit course ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment: 0
 */

public class ScintillationGrid {

	// Main method that creates the DrawingPanel with scintillation grids.
	// Restricted to chapters 1 - 3 of Building Java Programs
    public static void main(String[] args) {
    	/* In the final version of the program DO NOT call method drawingOne 
    	   from main or anywhere else in the program */
    	DrawingPanel p1 = new DrawingPanel(900, 650);
    	p1.setBackground(Color.CYAN);
    	Graphics g = p1.getGraphics();
    	
    	// Draw a series of scintillation grids. Input is X position, Y position, square size,
    	// number of lines, and g for the graphics object call
    	
    	drawGrid(0, 0, 348, 3, g);
    	drawGrid(400, 50, 422, 6, g);
    	drawGrid(50, 400, 220, 1, g);
    	drawGrid(500, 500, 148, 7, g);
    }
    
    
    // method for the student designed drawing
    // NOT restricted to chapters 1 - 3 of Building Java Programs
    
    /* Method for drawing a Yin Yang on a grey background. Calls a new drawing panel to keep from interfering with
    * the first. White and black (which side is which) are randomized.
    * I was totally going to animate this (have it spin and shift color), but we didn't get the sleep method
    */
    public static void drawingOne() {
    	int panelWidth = 800;
    	int panelHeight = panelWidth;
    	DrawingPanel p2 = new DrawingPanel(panelWidth, panelHeight);
    	p2.setBackground(Color.DARK_GRAY);
    	
    	Graphics g = p2.getGraphics();
    	
    	// Determine colors
    	double colorTest = Math.random();
    	Color colorOne = Color.black;
    	Color colorTwo = Color.white;
    	if (colorTest < 0.4) {
    		colorOne = Color.white;
    		colorTwo = Color.black;
    	}
    		
    	
    	// Make the frame
    	int frameWidth = panelWidth - 20;
    	int frameEdge = 10;
    	int largeOvalWidth = frameWidth / 2;
    	int smallOvalWidth = largeOvalWidth / 3;
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(10, 10, frameWidth, frameWidth);
    	
    	//make corner lines
    	g.setColor(Color.BLACK);
    	g.drawLine(frameEdge, frameEdge + frameWidth / 8, frameEdge + frameWidth / 8, 10);
    	g.drawLine(frameEdge + frameWidth * 7 / 8, 11, 9 + frameWidth, frameEdge + frameWidth / 8);
    	g.drawLine(frameEdge, frameEdge + frameWidth * 7 / 8, 10 + frameWidth / 8, 9 + frameWidth);
    	g.drawLine(frameEdge + frameWidth * 7 / 8, 9 + frameWidth, 9 + frameWidth, frameEdge + frameWidth * 7 /8);
    	
    	//Draw the Yin Yang
    	
    	//outline
    	g.setColor(colorOne);
    	g.fillOval(frameEdge, frameEdge, frameWidth, frameWidth);
    	
    	//making the Yin Yang
		g.setColor(colorTwo);
    	g.fillArc(frameEdge, frameEdge, frameWidth, frameWidth, 90, 180);
    	g.fillOval(frameEdge + frameWidth / 4, frameEdge, largeOvalWidth, largeOvalWidth);
    	g.setColor(colorOne);
    	g.fillOval(frameEdge + frameWidth * 5 / 12, frameEdge + frameWidth / 6, smallOvalWidth, smallOvalWidth);
    	g.fillOval(frameEdge + frameWidth / 4, frameEdge + largeOvalWidth, largeOvalWidth, largeOvalWidth);
    	g.setColor(colorTwo);
    	g.fillOval(frameEdge + frameWidth * 5 / 12, frameEdge + frameWidth * 2 / 3, smallOvalWidth, smallOvalWidth);
    }
    
    
    /* 
    * SCINTILLATION GRID METHOD
    * Method to draw a scintillation grid. Calls drawLines and drawCircles for those respective functions.
    * Inputs are X Position, Y Position, Square Size, Number of Lines, and the graphics object.
    * X and Y positions reference the top left corner of the frame.
    * Draws all grids on the panel called in the main method.
    * I wanted to minimize the number of required parameters, so all that needs to be input is the size and
   	* the number of lines along with the position.
    */   
    public static void drawGrid(int xPosition, int yPosition, int squareSize, int numberLines, Graphics g) {
    	// Determine line thickness and small square size based on the overall size and the number of lines.
    	int lineThickness = 2 * ((squareSize / (numberLines + 1) + 2) / 10 - squareSize / ((numberLines + 1) * 100));
    	int smallSquareSize = (squareSize - numberLines * lineThickness) / (1 + numberLines);
    	
    	// Make the background for the grid
    	g.setColor(Color.BLACK);
    	g.fillRect(xPosition, yPosition, squareSize, squareSize);
    	
    	// Draw lines and circles via methods
    	drawLines(xPosition, yPosition, squareSize, smallSquareSize, lineThickness, numberLines, g);
    	drawCircles(xPosition, yPosition, smallSquareSize, lineThickness, numberLines, g);
    }
    
    // SCINTILLATION GRID HORIZONTAL AND VERTICAL LINES METHOD
    // Parameters are the same as called by drawGrid and are automatically passed by the drawGrid method
    public static void drawLines(int xPosition, int yPosition, int squareSize, int smallSquareSize, 
    			int lineThickness, int numberLines, Graphics g) {
    	
    	int startX = xPosition + smallSquareSize;
    	int startY = yPosition + smallSquareSize;
    	
    	// Make numberLines vertical lines and numberLines horizontal lines, spaced appropriately
		g.setColor(Color.GRAY);
    	for (int vertLines = 0; vertLines < numberLines; vertLines++) {
    		g.fillRect(startX, yPosition, lineThickness, squareSize);
    		startX += smallSquareSize + lineThickness;
    	}
    	for (int horizLines = 0; horizLines < numberLines; horizLines++) {
    		g.fillRect(xPosition, startY, squareSize, lineThickness);
    		startY += smallSquareSize + lineThickness;
    	}
    }
    
    // SCINTILLATION GRID CIRCLE METHOD
    // Parameters are the same as called by drawGrid, other than squareSize, and are automatically 
    // passed by the drawGrid method
    public static void drawCircles(int xPosition, int yPosition, int smallSquareSize, int lineThickness, 
    			int numberLines, Graphics g) {
    	
    	// Variable declarations
    	int circleSize = Math.max(lineThickness + 2, lineThickness * 14 / 10);
    	int halfCircleSize = circleSize / 2;
    	int startX = xPosition + smallSquareSize + lineThickness / 2 - halfCircleSize;
    	int startY = yPosition + smallSquareSize + lineThickness / 2 - halfCircleSize;
    	
    	// Make numberLines rows of numberLines white dots, spaced appropriately
    	g.setColor(Color.WHITE);
    	for (int numRows = 0; numRows < numberLines; numRows++) {
    		for (int numColumns = 0; numColumns < numberLines; numColumns++) {
    			g.fillOval(startX, startY, circleSize, circleSize);
    			startX += lineThickness + smallSquareSize;
    		}
    		startX = xPosition + smallSquareSize + lineThickness / 2 - halfCircleSize;
    		startY += lineThickness + smallSquareSize;
    	}
    }
}

