package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Receiver;
import view.menubar.MenuBuilder;

public class Display {
	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/display");
	private Scene myScene;
	private static  BorderPane myRoot;
	private MenuBar myMenuBar;
	private static  TabPane myWorkspaceDisplays;
	private static Collection<Workspace> myWorkspaces;
//	private static Workspace myWorkspace;
	private Feed myFeed;

	protected Display(Receiver receiver) {
		myRoot = new BorderPane();
		myFeed = new Feed(receiver);
		myWorkspaces = new ArrayList<>();
		setupWorkspaces(receiver);
		myRoot.setBottom(myFeed.getFeed());
		myRoot.setTop(makeMenuBar());
		myRoot.setCenter(myWorkspaceDisplays);

		// myRoot.setCenter(makeWorkspace(receiver));
		myScene = new Scene(myRoot, Integer.parseInt(myValues
				.getString("Width")), Integer.parseInt(myValues
				.getString("Height")));
	}

	private void setupWorkspaces(Receiver receiver) {
		myWorkspaceDisplays = new TabPane();
		Tab tab = new Tab();
		tab.setContent(makeWorkspace(receiver));
		myWorkspaceDisplays.getTabs().add(tab);
	}

	private Node makeWorkspace(Receiver receiver) {
		Workspace myWorkspace = new Workspace();
		Node workspaceNode = myWorkspace.init(receiver);
		myWorkspaces.add(myWorkspace);
		
		return workspaceNode;
	}

	private Node makeMenuBar() {
		MenuBuilder defaultMenuBuilder = new MenuBuilder(new MenuBar(),
				"Default");
		myMenuBar = defaultMenuBuilder.build();
		return myMenuBar;
	}

	public Scene getScene() {
		return this.myScene;
	}

	public static BorderPane getRoot() {
		return myRoot;
	}

//	public static Workspace getWorkspace() {
//		return myWorkspace;
//	}

	public static Workspace getSelectedWorkspace() {
		for (Tab currentTab : myWorkspaceDisplays.getTabs()) {
			if (currentTab.isSelected())
				for (Workspace currentWS: myWorkspaces) {
					if (currentWS.getRoot().equals(currentTab.getContent()))
						return currentWS;
				}
		}
		return null;
	}
}
