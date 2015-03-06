package view;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import model.Receiver;

public class WorkspaceDisplay {

	private BorderPane myRoot;
	private TurtleView myTurtleView;
	private HistoryPane myHistory;
	private VariablePane myVariables;
	private CommandPane myCommands;
	private TabPane myHistories;
	private int myID;
	private int TAB_MIN_WIDTH = 30;

	public WorkspaceDisplay(int id) {
		myID = id;
	}

	public Node init(Receiver receiver) {
		myRoot = new BorderPane();
		myHistories = new TabPane();
		myHistories.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myHistories.setTabMinWidth(TAB_MIN_WIDTH);
		myRoot.setRight(makeHistory(receiver));
		myRoot.setCenter(makeTurtleView());

		Feed feed = new Feed(receiver, this.getID());
		myRoot.setBottom(feed.getFeed());
		myRoot.setLeft(makeVariables(receiver, feed));
		myRoot.setRight(myHistories);
		makeCommands(feed);
		return myRoot;
	}

	private Node makeHistory(Receiver receiver) {
		myHistory = new HistoryPane(receiver, myID);
		Node histNode = myHistory.init();
		Tab tab = new Tab();
		tab.setContent(histNode);
		myHistories.getTabs().add(tab);
		return histNode;
	}

	private Node makeVariables(Receiver receiver, Feed feed) {
		myVariables = new VariablePane(receiver, myID, feed);
		Node varNode = myVariables.init();
		Tab tab = new Tab();
		tab.setContent(varNode);
		myHistories.getTabs().add(tab);
		return varNode;
	}

	private Node makeCommands(Feed feed) {
		myCommands = new CommandPane(feed);
		Node cmdsNode = myCommands.init();
		Tab tab = new Tab();
		tab.setContent(cmdsNode);
		myHistories.getTabs().add(tab);
		return cmdsNode;
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

	public CommandPane getCommandPane() {
		return myCommands;
	}

	public int getID() {
		return myID;
	}

}
