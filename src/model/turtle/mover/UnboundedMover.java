package model.turtle.mover;

import javafx.geometry.Point2D;
import model.line.SingleLine;
import model.turtle.PolarVector;
import model.turtle.SingleTurtle;


public class UnboundedMover implements Mover {

    public UnboundedMover () {
        super();
    }

    @Override
    public void moveTurtle (SingleTurtle turtle, PolarVector vector) {
        double r = vector.getRadius();
        double theta = vector.getTheta();
        turtle.setHeading(turtle.getHeading() + theta);

        // switched sine and cosine to flip x and y axes
        double x = r * Math.sin(Math.toRadians(turtle.getHeading()));
        // multiply by negative one to flip y axis.
        double y = -1 * r * Math.cos(Math.toRadians(turtle.getHeading()));
        Point2D cartesianVector = new Point2D(x, y);
        turtle.setPosition(turtle.getPosition().add(cartesianVector));
        drawLines(turtle);
        
    }

    @Override
    public void drawLines (SingleTurtle turtle) {
        if (!turtle.isPenUp()) {
            SingleLine temp = new SingleLine(turtle.getLastPosition(),
                                             turtle.getPosition());
            turtle.addLine(temp);
            System.out.println(temp.toString());
        }
    }

}
