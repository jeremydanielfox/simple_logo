package view;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;

public class Workspace extends BorderPane {
	private Node myTurtleView;
	private static final ResourceBundle myValues = ResourceBundle.getBundle(
			"resources/workspace/values", new Locale("workspace"));

	protected Workspace() {
		myTurtleView = makeTurtleView();
	}

	private Node makeTurtleView() {
		Canvas tv = new Canvas(
				Integer.parseInt(myValues.getString("TV_Width")),
				Integer.parseInt(myValues.getString("TV_Height")));
		return tv;
	}
}
