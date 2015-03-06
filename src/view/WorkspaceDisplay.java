package view;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Receiver;

public class WorkspaceDisplay {

	private BorderPane myRoot;
	private Feed myFeed;
	private TurtleView myTurtleView;
	private HistoryPane myHistory;
	private VariablePane myVariables;
	private CommandPane myCommands;
	private TabPane myHistories;
	private Console myConsole;
	private int myID;
	private final int TAB_MIN_WIDTH = 30;
	private final int CONSOLE_WIDTH = 350;

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
		Feed myFeed = new Feed(receiver, this.getID());
		
		HBox hb = new HBox();
		hb.getChildren().addAll(myFeed.getFeed(),this.makeConsole());
		myRoot.setBottom(hb);
		myRoot.setLeft(makeVariables(receiver, myFeed));
		myRoot.setRight(myHistories);
		makeCommands(myFeed);
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
	
	private Node makeConsole() {
		myConsole = new Console();
		myConsole.init(CONSOLE_WIDTH);
		Node consoleNode = myConsole.getConsole();
		return consoleNode;
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
	
	public Drawer getDrawer() {
		return myTurtleView;
	}

	public Configurable getConfigurable() {
		return myTurtleView;
	}
	
	public Historian getConsole() {
		return myConsole;
	}

}
