package assignment7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


/**
 * This class is allows different actions 
 * to be done to both rectangles and lines
 * @author Ryan
 *
 */
public abstract class Shape {
	private Color color;
	/**
	 * abstract draw method used
	 * to draw the different shapes
	 * @param g graphics
	 */
	public abstract void draw(Graphics g);
	
	
	
	/**
	 * abstract contain method used to determine
	 * if x and y coordinates in certain shapes
	 * @param x clicked x coord
	 * @param y clicked y coord
	 * @return boolean true or false
	 */
	public abstract boolean contains(int x, int y);
	
	
	
	/**
	 * the randomColor method adds a random color to the
	 * shape drawn
	 * @return shape color
	 */
	public Color randomColor() {
		Color lineColor = Color.red;
		Random rand = new Random(); 
		int value = rand.nextInt(4);
		if (value==0) {
			lineColor = Color.red;
			
		}
		else if (value==1) {
			lineColor = Color.green;
		
		}
		else if (value==2) {
			lineColor = Color.blue;
			
		}
		else if (value==3) {
			lineColor = Color.magenta;
			
		}
		return lineColor;
	}
	
	
	
	/**
	 * this method changes the string to a color
	 * for when opening up files, it can then be 
	 * re drawn
	 * @param s String
	 * @return shape color
	 */
	public Color stringToColor(String s) {
		Color shapeColor = null;
		if (s.equals("red")) {
			shapeColor = Color.red;
		}
		else if (s.equals("green")) {
			shapeColor = Color.green;
		}
		else if (s.equals("blue")) {
			shapeColor = Color.blue;
		}
		else if (s.equals("magenta")) {
			shapeColor = Color.magenta;
		}
		
		return shapeColor;
	}
	
	

	/**
	 * used to set the color of the shape
	 * @param c
	 */
	public void setColor(Color c) {
		color = c;
	}
	
	
	
	/**
	 * used to return the color of the
	 * shape
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	
	
	
	/**
	 * Changes the shape color to a string
	 * so it can be written to a text file
	 * for saving
	 * @param c color
	 * @return Color string
	 */
	public String colorToString(Color c) {
		String colorString = null;
		if (c==Color.red) {
			colorString = "red";
		}
		else if (c==Color.green) {
			colorString = "green";
		}
		else if (c==Color.blue) {
			colorString = "blue";
		}
		else if (c==Color.magenta) {
			colorString = "magenta";
		}
		
		return colorString;
	}
	

}
