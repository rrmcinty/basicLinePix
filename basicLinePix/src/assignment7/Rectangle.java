package assignment7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

/**
 * this class contains draw methods, getter, and contain
 * method for the rectangle shape
 * @author Ryan
 *
 */
public class Rectangle extends Shape{
	private Color color;
	private int topLeftX, topLeftY, bottomRightX, bottomRightY;

	String sColor;
	
	/**
	 * Constructor for when creating a new rectangle,
	 * and assign it a random color
	 * @param lx top left x coord
	 * @param ly top left y coord
	 * @param rx bottom right x coord
	 * @param ry bottom right y coord
	 */
	public Rectangle(int lx, int ly, int rx, int ry) {
		color = randomColor();
		topLeftX = lx;
		topLeftY = ly;
		bottomRightX = rx;
		bottomRightY = ry;
		sColor = colorToString(color);
	}
	
	
	/**
	 * Constructor for when creating a rectangle from
	 * a saved text file and assign it the designated 
	 * color using the stringToColor method
	 * @param lx top left x coord
	 * @param ly top left y coord
	 * @param rx bottom right x coord
	 * @param ry bottom right y coord
	 * @param c shape color
	 */
	public Rectangle(int lx, int ly, int rx, int ry, String c) {
		color = stringToColor(c);
		topLeftX = lx;
		topLeftY = ly;
		bottomRightX = rx;
		bottomRightY = ry;
	}
	
	
	/**
	 * overides the string method for rectangle,
	 * used to save rectangle to text files
	 */
	public String toString() {
		return "Rectangle" + " " + topLeftX + " " + topLeftY + " " + bottomRightX + " " + bottomRightY + " " + sColor;
	}
	
	/**
	 * getter for top left x coord
	 * @return top left x int
	 */
	public int getTLX() {
		return topLeftX;
	}
	
	/**
	 * getter for top left y coord
	 * @return top left y int
	 */
	public int getTLY() {
		return topLeftY;
	}
	
	/** 
	 * getter for bottom right x coord
	 * @return bottom right x int
	 */
	public int getBRX() {
		return bottomRightX;
	}
	
	
	/**
	 * getter for bottom right y coord
	 * @return bottom right y int
	 */
	public int getBRY() {
		return bottomRightY;
	}
	
	
	/**
	 * draw method for the shape rectangle.
	 * draws a line from each corner of the rectangle
	 * and assigns it a random color
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(topLeftX, topLeftY, bottomRightX, topLeftY);
		g.drawLine(bottomRightX, topLeftY, bottomRightX, bottomRightY);
		g.drawLine(bottomRightX, bottomRightY, topLeftX, bottomRightY);
		g.drawLine(topLeftX, bottomRightY, topLeftX, topLeftY);
	}
	
	
	/**
	 * contains method to determine if the 
	 * x and y coordinate is in the rectangle
	 */
	public boolean contains(int x, int y) {
		//creates two arrayList for x coordinates and y coord
		
		//x coord array
	    int xStart = topLeftX;
	    int xEnd = bottomRightX;
	    ArrayList<Integer> xArray = new ArrayList<Integer>();
	    for(int i = xStart+1; i < xEnd; i++) {
	        xArray.add(i);          
	    }
	    
	    //y coord array
	    int yStart = topLeftY;
	    int yEnd = bottomRightY;

	    ArrayList<Integer> yArray = new ArrayList<Integer>();
	    for(int i = yStart+1; i < yEnd; i++) {
	        yArray.add(i); 
	    }

		boolean contains = false;
		/*iterates through the shape to determine if the x and y coordinates
		are located in the shape using the array lists*/
		for (int i = 0 ; i<xArray.size() ; i++) {
			for (int j = 0 ; j<yArray.size() ; j++) {
				if ((xArray.get(i)==x) && (yArray.get(j)==y)) {
					contains = true;
				}
				else {
					continue;
				}
			}
		}
		
		return contains;
		
	}
}
