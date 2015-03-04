package view;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;


import javafx.scene.Group;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Model;
import model.Receiver;
import model.database.Database;
import view.menubar.MenuBuilder;
import Exceptions.BadResourcePackageException;

public class Display {
	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/display");
	private static Display instance;

	private Scene myScene;
	private BorderPane myRoot;
	private MenuBar myMenuBar;
	private TabPane myWorkspaceDisplays;
	private Collection<WorkspaceDisplay> myWorkspaces;
	private Feed myFeed;
	private Model myModel;
	private static final double TAB_MIN_WIDTH = 50;

	protected Display() {
		myRoot = new BorderPane();
		myWorkspaces = new ArrayList<>();
	}

	public static Display getInstance() {
		if (instance == null)
			instance = new Display();
		return instance;
	}

	public Scene init(Model model) {
		myModel = model;
		myFeed = new Feed((Receiver) myModel);
		setupWorkspaces((Receiver) myModel);
		try {
			makeMenuBar();
		} catch (BadResourcePackageException e) {
			e.printStackTrace();
		}
		myRoot.setCenter(myWorkspaceDisplays);

		myScene = new Scene(myRoot, Integer.parseInt(myValues
				.getString("Width")), Integer.parseInt(myValues
				.getString("Height")));

		return this.myScene;
	}

	private void setupWorkspaces(Receiver receiver) {
		myWorkspaceDisplays = new TabPane();
		myWorkspaceDisplays.setTabMinWidth(TAB_MIN_WIDTH);
		makeWorkspaceDisplay(receiver);
	}

	public void makeWorkspaceDisplay(Receiver receiver) {
		WorkspaceDisplay myWorkspace = new WorkspaceDisplay();
		Node workspaceNode = myWorkspace.init(receiver);
		myWorkspaces.add(myWorkspace);
		Tab tab = new Tab();
		tab.setContent(workspaceNode);
		myWorkspaceDisplays.getTabs().add(tab);
	}

	private void makeMenuBar() throws BadResourcePackageException {
		myMenuBar = new MenuBar();
		File dir = new File("src/resources/menus/");
		File[] dirArray = dir.listFiles();
		if (dirArray != null) {
			for (File file : dirArray) {
				String source = file.getName().replaceAll(".properties", "");
				ResourceBundle rb = ResourceBundle.getBundle("resources/menus/"
						+ source);
				ArrayList<Object> params = new ArrayList<Object>();
				if (rb.containsKey("Params")) {
					for (String s : rb.getString("Params").split(", ")) {
						try {
							Object o = Class
									.forName(s)
									.getDeclaredMethod("getInstance",
											(Class<?>[]) null)
									.invoke(null, (Object[]) null);
							params.add(o);
						} catch (IllegalAccessException
								| IllegalArgumentException
								| InvocationTargetException
								| NoSuchMethodException | SecurityException
								| ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
				MenuBuilder mb = new MenuBuilder(myMenuBar, rb, params);
				myMenuBar = mb.build();
			}
		} else {
			throw new BadResourcePackageException();
		}
		myRoot.setTop(myMenuBar);
	}

	public BorderPane getRoot() {
		return myRoot;
	}

	public WorkspaceDisplay getSelectedWorkspace() {
		for (Tab currentTab : myWorkspaceDisplays.getTabs()) {
			if (currentTab.isSelected())
				for (WorkspaceDisplay currentWS : myWorkspaces) {
					if (currentWS.getRoot().equals(currentTab.getContent()))
						return currentWS;
				}
		}
		return null;
	}
}
