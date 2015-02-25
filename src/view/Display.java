package view;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

import view.menubar.MenuBuilder;
import model.Receiver;

public class Display {
	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/display");
	private static Display instance;

	private Scene myScene;
	private BorderPane myRoot;
	private TurtleView myTurtleView;
	private MenuBar myMenuBar;
	private Workspace myWorkspace;
	private static Feed myFeed;

	private Display(Receiver myReceiver) {
		myRoot = new BorderPane();
		myTurtleView = new TurtleView();
		myFeed = Feed.getInstance(myReceiver);
		myRoot.setBottom(myFeed.getFeed());
		myRoot.setTop(makeMenuBar());
		myRoot.setCenter(makeWorkspace(myTurtleView));
		myScene = new Scene(myRoot, Integer.parseInt(myValues
				.getString("Width")), Integer.parseInt(myValues
				.getString("Height")));
	}

	private Node makeWorkspace(TurtleView myTV) {
		myWorkspace = new Workspace(myTV);
		Node workspaceNode = myWorkspace.init();
		return workspaceNode;
	}

	protected static Display getInstance(Receiver myReceiver) {
		if (instance == null)
			instance = new Display(myReceiver);
		return instance;
	}

	private Node makeMenuBar() {
		MenuBuilder defaultMenuBuilder = new MenuBuilder(new MenuBar(), "Default");
		myMenuBar = defaultMenuBuilder.build();
		return myMenuBar;
	}

	public Scene getScene() {
		return this.myScene;
	}

}
