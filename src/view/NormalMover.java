package view;

import javafx.geometry.Point2D;

public class NormalMover implements TurtleMover {

	@Override
	public void moveTurtle(Turtle turtle, Point2D changepos) {
		// TODO Auto-generated method stub
		turtle.move(changepos.getX(), changepos.getY());

	}

	@Override
	public void drawLines() {
		// TODO Auto-generated method stub

	}

}
