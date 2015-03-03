package model;

import turtle.SingleTurtle;


public interface Mover {

	public void moveTurtle(SingleTurtle turtle, PolarVector vector);

	public void drawLines(SingleTurtle turtle);
}
