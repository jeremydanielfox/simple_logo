package view;

import view.turtleview.TurtleView;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class Workspace {

	BorderPane myRoot;
	TurtleView myTurtleView;
	HistoryPane myHistory;
	VariablePane myVariables;

	public Workspace() {

	}

	public Node init() {
		myRoot = new BorderPane();
		myRoot.setRight(makeHistory());
		myRoot.setCenter(makeTurtleView());
		myRoot.setLeft(makeVariables());
		return myRoot;
	}

	private Node makeHistory() {
		myHistory = new HistoryPane();
		Node histNode = myHistory.init();
		return histNode;
	}

	private Node makeVariables() {
		myVariables = new VariablePane();
		Node varNode = myVariables.init();
		return varNode;
	}

	private Node makeTurtleView() {
		myTurtleView = new TurtleView();
		Node tvNode = myTurtleView.getView();
		return tvNode;
	}

}
