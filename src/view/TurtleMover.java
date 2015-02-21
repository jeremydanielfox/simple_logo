package view;

import javafx.geometry.Point2D;

public interface TurtleMover {
	public void moveTurtle(Turtle turtle, Point2D translation);

	public void drawLines();
}
