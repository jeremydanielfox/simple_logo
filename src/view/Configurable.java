package view;

import java.util.Map;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * 
 * @author Jeremy
 *
 *         Allows for a viewable and configurable display
 */
public interface Configurable {
	/**
	 * Takes in Color and sets the pen to that color
	 * 
	 * @param color
	 */
	public void setPenColor(Color color);

	/**
	 * Sets the pen width to a given double
	 * 
	 * @param width
	 */
	public void setPenWidth(double width);

	/**
	 * Takes in a Color and sets the display background to that color
	 * 
	 * @param color
	 */
	public void setBackgroundColor(Color color);

	/**
	 * Returns a Node containing all onscreen objects
	 * 
	 * @return
	 */
	public Node getView();

	/**
	 * Sets the image of the onscreen Turtles
	 * 
	 * @param img
	 */
	public void setTurtleImage(Image img);
	
	public Map<Integer,Image> getTurtles();
}
