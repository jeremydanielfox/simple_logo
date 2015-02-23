package model;

import javafx.geometry.Point2D;

public class Turtle {
	private Point2D myPosition;
	private double myHeading;
	private boolean visible;
	private boolean penUp;
	private static final Point2D HOME = new Point2D(0,0);
	
	protected Turtle() {
		myPosition = HOME;
		myHeading = 0;
		visible = true;
		penUp = false;
	}
	
	protected void setPosition(Point2D position) {
		myPosition = position;
	}
	
	protected void goHome() {
		setPosition(HOME);
	}
	
	protected void setHeading(double heading) {
		myHeading = heading;
	}
	protected Point2D getHome() {
		return HOME;
	}
	protected Point2D getPosition() {
		return myPosition;
	}
	
	protected double getHeading() {
		return myHeading;
	}
	
	protected void toggleVisibility() {
		visible = !visible;
	}
	
	protected void togglePen() {
		penUp = !penUp;
	}
	
	protected boolean isVisible() {
		return visible;
	}
	
	protected boolean isPenUp() {
		return penUp;
	}

}
