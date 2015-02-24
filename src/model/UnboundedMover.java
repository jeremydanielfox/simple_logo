package model;

import javafx.geometry.Point2D;


public class UnboundedMover implements Mover {

    protected UnboundedMover () {
        super();
    }

    @Override
    public void moveTurtle (Turtle turtle, PolarVector vector) {
        double r = vector.getRadius();
        double theta = vector.getTheta();
        double x = r * Math.cos(Math.toRadians(theta));
        double y = r * Math.sin(Math.toRadians(theta));
        Point2D CartesianVector = new Point2D(x, y);
        turtle.setHeading(turtle.getHeading() + theta);
        turtle.setPosition(turtle.getPosition().add(CartesianVector));
        drawLines(turtle);
    }
    
    @Override
    public void drawLines (Turtle turtle) {
        if (!turtle.isPenUp()){
            turtle.addLine(new LineData(turtle.getPreviousPosition(), turtle.getPosition()));
        }
    }

}
