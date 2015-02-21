package view;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TurtleView {

	private static final ResourceBundle myValues = ResourceBundle.getBundle(
			"resources/display/values", new Locale("turtleview"));
	private static final String TURTLE_X_POSITION = "Turtle xpos";
	private static final String TURTLE_Y_POSITION = "Turtle ypos";

	Group myGroup;
	Stage myStage;
	Scene myScene;

	public TurtleView() {

	}

	public Scene init(Stage s, double width, double height) {
		myGroup = new Group();
		myStage = s;
		myScene = new Scene(myGroup, width, height);
		return myScene;
	}

	public void addNode(Node toAdd) {

	}

	public void addTurtle() {
		Turtle turtle = new Turtle(Integer.parseInt(myValues
				.getString(TURTLE_X_POSITION)), Integer.parseInt(myValues
				.getString(TURTLE_Y_POSITION)));
		myGroup.getChildren().add(turtle);
	}
}
