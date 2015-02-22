package view;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;

public class TurtleView {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/turtleview");

	Canvas myRoot;

	public TurtleView() {

	}

	public Canvas init() {
		myRoot = new Canvas(Integer.parseInt(myValues.getString("Width")),
				Integer.parseInt(myValues.getString("Height")));
		return myRoot;
	}

	public void addNode(Node toAdd) {

	}

	public void addTurtle() {
		Turtle turtle = new Turtle(
				Integer.parseInt(myValues.getString("Turtle xpos")), 
				Integer.parseInt(myValues.getString("Turtle ypos")));

	}

	public void addTurtle(int x, int y) {
		Turtle turtle = new Turtle(x, y);
	}

}
