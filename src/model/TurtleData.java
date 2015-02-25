package model;

public class TurtleData {
	private double myXCoord;
	private double myYCoord;
	private double myHeading;
	private int myID;

	public TurtleData(double x, double y, double h, int id) {
		myXCoord = x;
		myYCoord = y;
		myHeading = h;
		myID = id;
	}

	public double getX() {
		return myXCoord;
	}

	public double getY() {
		return myYCoord;
	}

	public double getHeading() {
		return myHeading;
	}

	private int getID() {
		return myID;
	}
}
