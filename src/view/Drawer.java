// This entire file is part of my masterpiece
// Jeremy Fox
package view;

import javafx.geometry.Point2D;
import model.Drawable;

/**
 * The Visitor part of the Visitor pattern, the Drawer takes in Drawable objects
 * and draws them. It also has clearing functionality.
 * 
 * @author Jeremy
 *
 */
public interface Drawer {
	/**
	 * Draws a line given a start and end point
	 * 
	 * @param start
	 * @param end
	 */
	public void drawLine(Point2D start, Point2D end);

	/**
	 * Draws a Turtle given a location and heading
	 * 
	 * @param location
	 * @param heading
	 */
	public void drawTurtle(Point2D location, double heading);

	/**
	 * Clears all turtles
	 */
	public void clearTurtles();

	/**
	 * Clears all lines
	 */
	public void clearLines();

}
