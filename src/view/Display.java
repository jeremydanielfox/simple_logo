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
	private static BorderPane myRoot;
	private MenuBar myMenuBar;
	private static Workspace myWorkspace;
	private static Feed myFeed;

	private Display(Receiver myReceiver) {
		myRoot = new BorderPane();
		myFeed = Feed.getInstance(myReceiver);
		myRoot.setBottom(myFeed.getFeed());
		myRoot.setTop(makeMenuBar());
		myRoot.setCenter(makeWorkspace());
		myScene = new Scene(myRoot, Integer.parseInt(myValues
				.getString("Width")), Integer.parseInt(myValues
				.getString("Height")));
	}

	private Node makeWorkspace() {
		myWorkspace = new Workspace();
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
	
	public static BorderPane getRoot() {
		return myRoot;
	}
	
	public static Workspace getWorkspace() {
		return myWorkspace;
	}

}
