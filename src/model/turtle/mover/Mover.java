package model.turtle.mover;

import model.turtle.PolarVector;
import model.turtle.SingleTurtle;

/**
 * Generalizes the way a turtle could be moved and the method of drawing its lines.
 * @author Nathan Prabhu
 *
 */
public interface Mover {

	public void moveTurtle(SingleTurtle turtle, PolarVector vector);

	public void drawLines(SingleTurtle turtle);
}
