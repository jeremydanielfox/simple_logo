package model;

import javafx.geometry.Point2D;

public class NormalMover implements TurtleMover {

	@Override
	public void moveTurtle(Turtle turtle, PolarVector vector) {
		double r = vector.getRadius();
		double theta = vector.getTheta();
		double x = r * Math.cos(Math.toRadians(theta));
		double y = r * Math.sin(Math.toRadians(theta));
		Point2D deltaPosition = new Point2D(x, y);
		turtle.setHeading(turtle.getHeading() + theta);
		turtle.setPosition(turtle.getPosition().add(deltaPosition));
	}

	@Override
	public double rotateTurtle(Turtle turtle, double rotation) {
		moveTurtle(turtle, new PolarVector(0, rotation));
		return rotation;

	}

	@Override
	public void drawLines() {
		// TODO Auto-generated method stub

	}

	@Override
	public double goHome(Turtle turtle) {
		Point2D home = turtle.getHome();
		Point2D currentPosition = turtle.getPosition();
		double distance = home.distance(currentPosition);
		double heading = currentPosition.angle(home);
		moveTurtle(turtle, new PolarVector(distance, heading));
		return distance;

	}

	@Override
	public double translateTurtle(Turtle turtle, double distance) {
		moveTurtle(turtle, new PolarVector(distance, 0));
		return distance;
	}

}
