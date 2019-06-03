package assignment7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import assignment7.Shape;

/**
 * this class is used to obtain info from, create, and draw
 * line objects
 * @author Ryan
 *
 */
public class Line extends Shape{
	private int startX, startY, endX, endY;
	private Color color;
	private String sColor;
	
	/**
	 * constructor used to create a new line,
	 * and assigns it a random color
	 * @param sx starting x coord
	 * @param sy starting y coord
	 * @param ex ending x coord
	 * @param ey ending y coord
	 */
	public Line(int sx, int sy, int ex, int ey) {
		color = randomColor();
		startX = sx;
		startY = sy;
		endX = ex;
		endY = ey;
		sColor = colorToString(color);
	}
	
	/**
	 * constructor to open up a line from text file and 
	 * assign it the designated color using the stringToColor 
	 * method
	 * @param sx starting x coord
	 * @param sy starting y coord
	 * @param ex ending x coord
	 * @param ey ending y coord
	 * @param c color string
	 */
	public Line(int sx, int sy, int ex, int ey, String c) {

		color = stringToColor(c);
		startX = sx;
		startY = sy;
		endX = ex;
		endY = ey;
	}
	
	
	/**
	 * overrides toString method in order to save to a text file
	 */
	public String toString() {
		return "Line" + " " + startX + " " + startY + " " + endX + " " + endY + " " + sColor;
	}
	
	/**
	 * draw class used to draw the line
	 * using the start and end coords and
	 * set color
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(startX, startY, endX, endY);
	}


	
	/**
	 * return the start x coord of the line
	 * @return start x int
	 */
	public int getStartX() {
		return startX;
	}
	
	/**
	 * return the start y coord of the line
	 * @return start y int
	 */
	public int getStartY() {
		return startY;
	}
	
	
	/**
	 * return final x point
	 * @return current/final x int
	 */
	public int getX() {
		return endX;
	}
	
	/**
	 * return final y point
	 * @return current/final y int
	 */
	public int getY() {
		return endY;
	}
	
	/**
	 * contains method for the line class,
	 * and checks to see if the clicked point
	 * is on the line
	 */
	public boolean contains(int x, int y) {
		//distance of ac + bc = ab sqrt[(x2-x1)^2 (y2-y1)^2]
		//a startx, starty
		//b endx, endy
		//c x, y
		double disAC = Math.sqrt(Math.pow(x-startX,2)+Math.pow(y-startY, 2));
		double disBC = Math.sqrt(Math.pow(endX-x,2)+Math.pow(endY-y, 2));
		double added = Math.round(disAC+disBC);
		double disAB = Math.round(Math.sqrt(Math.pow(endX-startX,2)+Math.pow(endY-startY, 2)));
		if (added==disAB) {
			return true;
		}
		else {
				return false;
			}
	}
}
