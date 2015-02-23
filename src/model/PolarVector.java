package model;

public class PolarVector {
	private double myRadius;
	private double myTheta;

	protected PolarVector(double r, double t) {
		myRadius = r;
		myTheta = t;
	}

	protected double getRadius() {
		return myRadius;
	}

	protected double getTheta() {
		return myTheta;
	}
}
