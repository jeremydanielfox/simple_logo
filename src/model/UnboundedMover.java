package model;

import javafx.geometry.Point2D;

public class UnboundedMover implements Mover {

	public UnboundedMover() {
		super();
	}

	@Override
	public void moveTurtle(Turtle turtle, PolarVector vector) {
		double r = vector.getRadius();
		double theta = vector.getTheta();
		turtle.setHeading(turtle.getHeading() + theta);
		
		//switched sine and cosine to flip x and y axes
		double x = r * Math.sin(Math.toRadians(turtle.getHeading()));
		// multiply by negative one to flip y axis.
		double y = -1*r * Math.cos(Math.toRadians(turtle.getHeading()));
		Point2D CartesianVector = new Point2D(x, y);
		turtle.setPosition(turtle.getPosition().add(CartesianVector));
		drawLines(turtle);
	}

	@Override
	public void drawLines(Turtle turtle) {
		if (!turtle.isPenUp()) {
			LineData temp = new LineData(turtle.getPreviousPosition(),
					turtle.getPosition());
			turtle.addLine(temp);
			System.out.println(temp.toString());
		}
	}

}
