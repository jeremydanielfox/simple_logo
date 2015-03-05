package view;

import javafx.geometry.Point2D;
import model.Drawable;

public interface Drawer {
	public void draw(Drawable drawable);

	public void drawLine(Point2D start, Point2D end);

	public void drawTurtle(Point2D location, double heading, boolean visible, int id);

}
