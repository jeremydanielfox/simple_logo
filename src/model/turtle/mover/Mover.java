package model.turtle.mover;

import model.turtle.PolarVector;
import model.turtle.SingleTurtle;


public interface Mover {

	public void moveTurtle(SingleTurtle turtle, PolarVector vector);

	public void drawLines(SingleTurtle turtle);
}
