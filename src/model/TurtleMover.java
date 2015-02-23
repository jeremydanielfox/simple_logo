package model;


public interface TurtleMover {
	public double goHome(Turtle turtle);

	public void moveTurtle(Turtle turtle, PolarVector vector);

	public double translateTurtle(Turtle turtle, double distance);

	public double rotateTurtle(Turtle turtle, double rotation);

	public void drawLines();
}
