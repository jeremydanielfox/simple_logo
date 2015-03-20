package view;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Receiver;
/**
 * Represents the front end analogue of the Workspace, containing the Drawer, all HistoryPanes, and the Feed
 * @author Jeremy
 *
 */
public class WorkspaceDisplay {

	private BorderPane myRoot;
	private TurtleView myTurtleView;
	private HistoryPane myHistory;
	private VariablePane myVariables;
	private CommandPane myCommands;
	private PalletPane myPallet;
	private TabPane myHistories;
	private Console myConsole;
	private int myID;
	private final int TAB_MIN_WIDTH = 30;
	private final int CONSOLE_WIDTH = 350;
/**
 * Creates a new WorkspaceDisplay with the given ID
 * @param id
 */
	public WorkspaceDisplay(int id) {
		myID = id;
	}
/**
 * Initializes all workspace components.
 * @param receiver
 * @return
 */
	public Node init(Receiver receiver) {
		myRoot = new BorderPane();
		myHistories = new TabPane();
		myHistories.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myHistories.setTabMinWidth(TAB_MIN_WIDTH);
		myRoot.setCenter(makeTurtleView());
		Feed myFeed = new Feed(receiver, this.getID());
		
		HBox hb = new HBox();
		hb.getChildren().addAll(myFeed.getFeed(),this.makeConsole());
		myRoot.setBottom(hb);
		makeHistory(receiver);
		makeVariables(receiver, myFeed);
		makeCommands(myFeed);
		makePallet(receiver, myFeed);
		myRoot.setRight(myHistories);
		return myRoot;
	}

	private Node makeHistory(Receiver receiver) {
		myHistory = new HistoryPane(receiver, myID);
		Node histNode = myHistory.init("History");
		Tab tab = new Tab();
		tab.setContent(histNode);
		myHistories.getTabs().add(tab);
		return histNode;
	}

	private Node makeVariables(Receiver receiver, Feed feed) {
		myVariables = new VariablePane(receiver, myID, feed);
		Node varNode = myVariables.init("Variable");
		Tab tab = new Tab();
		tab.setContent(varNode);
		myHistories.getTabs().add(tab);
		return varNode;
	}

	private Node makeCommands(Feed feed) {
		myCommands = new CommandPane(feed);
		Node cmdsNode = myCommands.init("Command");
		Tab tab = new Tab();
		tab.setContent(cmdsNode);
		myHistories.getTabs().add(tab);
		return cmdsNode;
	}
	
	private Node makePallet(Receiver receiver, Feed feed) {
		myPallet = new PalletPane(receiver, myID, feed);
		Node pallNode = myPallet.init("Pallet");
		Tab tab = new Tab();
		tab.setContent(pallNode);
		myHistories.getTabs().add(tab);
		return pallNode;
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
