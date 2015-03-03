package view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import model.Receiver;
import model.database.Database;

public class WorkspaceDisplay {

	private BorderPane myRoot;
	private TurtleView myTurtleView;
	private HistoryPane myHistory;
	private VariablePane myVariables;
	private Database myDatabase;
	private static int myID = -1;

	public WorkspaceDisplay(Database db) {
		myID++;
	}

	public Node init(Receiver receiver) {
		myRoot = new BorderPane();
		myRoot.setRight(makeHistory(receiver));
		myRoot.setCenter(makeTurtleView());
		myRoot.setLeft(makeVariables(receiver));
		Feed feed = new Feed(receiver, myDatabase);
		myRoot.setBottom(feed.getFeed());
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

	public TurtleView getTurtleView() {
		return this.myTurtleView;
	}

	public Node getRoot() {
		return myRoot;
	}

	public VariablePane getVariablePane() {
		return myVariables;
	}

	public HistoryPane getHistoryPane() {
		return myHistory;
	}

}
