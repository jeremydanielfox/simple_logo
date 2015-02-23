package view;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import model.Receiver;

public class Display {
	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/display");
	private static Display instance;
	private Scene myScene;
	private BorderPane myRoot;
	private MenuBar myMenuBar;
	private Workspace myWorkspace;
	private static Feed myFeed;

	private Display(Receiver myReceiver) {
		myRoot = new BorderPane();
		myFeed = Feed.getInstance(myReceiver);
		myRoot.setBottom(myFeed);
		myRoot.setTop(makeMenuBar());
		myRoot.setCenter(makeWorkspace());
		myScene = new Scene(myRoot, Integer.parseInt(myValues
				.getString("Width")), Integer.parseInt(myValues
						.getString("Height")));
	}

	private Node makeWorkspace() {
		myWorkspace = new Workspace();
		BorderPane workspaceNode = myWorkspace.init();
		return workspaceNode;
	}

	protected static Display getInstance(Receiver myReceiver) {
		if (instance == null)
			instance = new Display(myReceiver);
		return instance;
	}

	private Node makeMenuBar() {

		myMenuBar = new MenuBar();
		myMenuBar.getMenus().add(makeMenu("File"));
		myMenuBar.getMenus().add(makeMenu("Edit"));
		myMenuBar.getMenus().add(makeMenu("View"));
		myMenuBar.getMenus().add(makeMenu("Options"));
		myMenuBar.getMenus().add(makeMenu("Help"));
		return myMenuBar;
	}

	private Menu makeMenu(String name) {
		Menu menu = new Menu(name);
		String[] arrayCharles = myValues.getString(name).split(", ");
		for (String s : arrayCharles) {
			MenuItem item = new MenuItem();
			item.setOnAction(e -> getMethodName(s));
			menu.getItems().add(item);
		}
		return menu;
	}

	private Method getMethodName(String s) {
		s.replaceAll(" ", "");
		String first = String.valueOf(s.charAt(0));
		s.replaceFirst("%c", first.toLowerCase());
		Method m;
		try {
			m = Display.class.getDeclaredMethod(s, (Class<?>[]) null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			m = null;
		}
		return m;
	}

	public Scene getScene() {
		return this.myScene;
	}

}
