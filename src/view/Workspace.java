package view;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.layout.BorderPane;

public class Workspace extends BorderPane {
	private TurtleView myTurtleView;
	private static final ResourceBundle myValues = ResourceBundle.getBundle(
			"view.resources.Values_workspace", new Locale("en", "US"));

	protected Workspace() {
		makeTurtleView();
		this.setCenter(myTurtleView.getView());
		// myGC = myTurtleView.getGraphicsContext2D();
	}

	private void makeTurtleView() {
		myTurtleView = new TurtleView();
	}

}
