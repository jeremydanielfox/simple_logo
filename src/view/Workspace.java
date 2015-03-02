package view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import model.Receiver;
import model.database.Database;

public class Workspace {

	private BorderPane myRoot;
	private TurtleView myTurtleView;
	private HistoryPane myHistory;
	private VariablePane myVariables;
	private Database myDatabase;

	public Workspace(Database db) {
		myDatabase = db;
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
		myHistory = new HistoryPane(receiver,myDatabase);
		Node histNode = myHistory.init();
		return histNode;
	}

	private Node makeVariables(Receiver receiver) {
		myVariables = new VariablePane(receiver,myDatabase);
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
	
	public VariablePane getVariablePane() {
		return myVariables;
	}

}
