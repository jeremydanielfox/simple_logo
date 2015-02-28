package view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import model.Receiver;

public class Workspace {

	BorderPane myRoot;
	TurtleView myTurtleView;
	HistoryPane myHistory;
	VariablePane myVariables;

	public Workspace() {
	}

	public Node init(Receiver receiver) {
		myRoot = new BorderPane();
		myRoot.setRight(makeHistory(receiver));
		myRoot.setCenter(makeTurtleView());
		myRoot.setLeft(makeVariables(receiver));
		return myRoot;
	}

	private Node makeHistory(Receiver receiver) {
		myHistory = new HistoryPane(receiver);
		Node histNode = myHistory.init();
		return histNode;
	}

	private Node makeVariables(Receiver receiver) {
		myVariables = new VariablePane(receiver);
		Node varNode = myVariables.init();
		return varNode;
	}

	private Node makeTurtleView() {
		myTurtleView = new TurtleView();
		Node tvNode = myTurtleView.getView();
		return tvNode;
	}

	public TurtleView getTV() {
		return this.myTurtleView;
	}
	
	public Node getRoot() {
		return myRoot;
	}

}
