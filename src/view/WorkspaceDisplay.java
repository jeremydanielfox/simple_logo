package view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import model.Receiver;
import model.database.OldDatabase;
import model.line.LineListCollection;

public class WorkspaceDisplay {

	private BorderPane myRoot;
	private TurtleView myTurtleView;
	private HistoryPane myHistory;
	private VariablePane myVariables;
	private int myID;

	public WorkspaceDisplay(int id) {
		myID = id;
	}

	public Node init(Receiver receiver) {
		myRoot = new BorderPane();
		myRoot.setRight(makeHistory(receiver));
		myRoot.setCenter(makeTurtleView());

		Feed feed = new Feed(receiver, this.getID());
		myRoot.setBottom(feed.getFeed());
		myRoot.setLeft(makeVariables(receiver, feed));
		return myRoot;
	}

	private Node makeHistory(Receiver receiver) {
		myHistory = new HistoryPane(receiver, myID);
		Node histNode = myHistory.init();
		return histNode;
	}

	private Node makeVariables(Receiver receiver, Feed feed) {
		myVariables = new VariablePane(receiver, myID, feed);
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

	public int getID() {
		return myID;
	}

}
