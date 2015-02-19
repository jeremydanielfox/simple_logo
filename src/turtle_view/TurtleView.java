package turtle_view;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TurtleView {
		
	private static final ResourceBundle myValues = ResourceBundle.getBundle("resources/display/values", new Locale("turtleview")); 
	
	Group myGroup;
	Stage myStage;
	Scene myScene;
	
	public TurtleView () {
		
	}
	
	public Scene init (Stage s, double width, double height) {
		myGroup = new Group();
		myStage = s;
		myScene = new Scene(myGroup, width, height);
		return myScene;
	}
	
	public void addNode(Node toAdd) {
		
	}
	
	public void addTurtle() {
		Turtle turtle = new Turtle (Integer.parseInt(myValues.getString("Turtle xpos")), Integer.parseInt(myValues.getString("Turtle ypos")));
		myGroup.getChildren().add(turtle);
	}

}
