package view;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class Workspace {

	BorderPane myRoot;
	TurtleView myTurtleView;
	HistoryPane myHistory;
	VariablePane myVariables;

	public Workspace() {

	}

	public BorderPane init() {
		myRoot = new BorderPane();
		myRoot.setRight(makeHistory());
		myRoot.setCenter(makeTurtleView());
		myRoot.setLeft(makeVariables());
		return myRoot;
	}

	private Node makeHistory() {
		myHistory = new HistoryPane();
		ScrollPane histNode = myHistory.init();
		return histNode;
	}

	private Node makeVariables() {
		myVariables = new VariablePane();
		ScrollPane varNode = myVariables.init();
		return varNode;
	}

	private Node makeTurtleView() {
		myTurtleView = new TurtleView();
		Canvas tvNode = myTurtleView.init();
		return tvNode;
	}

}
