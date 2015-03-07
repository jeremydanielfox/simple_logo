package model.turtle;

/**
 * A Polar Vector that represents a radius and angle.
 * @author Nate
 *
 */
public class PolarVector {
	private double myRadius;
	private double myTheta;

	public PolarVector(double r, double t) {
		myRadius = r;
		myTheta = t;
	}

	public double getRadius() {
		return myRadius;
	}

	public double getTheta() {
		return myTheta;
	}
}
