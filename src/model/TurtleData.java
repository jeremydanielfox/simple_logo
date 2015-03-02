package model;

import javafx.geometry.Point2D;

public class TurtleData implements Drawable {
	private double myXCoord;
	private double myYCoord;
	private double myHeading;
	private int myID;
	private boolean myVisible;

	public TurtleData(double x, double y, double h, int id, boolean visible) {
		myXCoord = x;
		myYCoord = y;
		myHeading = h;
		myID = id;
		myVisible = visible;
	}

	public double getX() {
		return myXCoord;
	}

	public double getY() {
		return myYCoord;
	}

	public void setLocation(Point2D location) {
		myXCoord = location.getX();
		myYCoord = location.getY();
	}

	public double getHeading() {
		return myHeading;
	}

	public void setHeading(double heading) {
		myHeading = heading;
	}

	public int getID() {
		return myID;
	}

	public boolean getVisible() {
		return myVisible;
	}

	public void setVisible(boolean visible) {
		myVisible = visible;
	}
}
