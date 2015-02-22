package view;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class Display {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/display");

	Scene myScene;
	BorderPane myRoot;

	public Display() {
		myRoot = new BorderPane();

		myRoot.setTop(makeMenuBar());
		myRoot.setCenter(makeWorkspace());
		myScene = new Scene(myRoot, Integer.parseInt(myValues
				.getString("Width")), Integer.parseInt(myValues
				.getString("Height")));
	}

	private Node makeWorkspace() {
		Workspace w = new Workspace();
		BorderPane workspaceNode = w.init();
		return workspaceNode;
	}

	private Node makeMenuBar() {

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(makeMenu("File"));
		menuBar.getMenus().add(makeMenu("Edit"));
		menuBar.getMenus().add(makeMenu("View"));
		menuBar.getMenus().add(makeMenu("Options"));
		menuBar.getMenus().add(makeMenu("Help"));
		return menuBar;
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
