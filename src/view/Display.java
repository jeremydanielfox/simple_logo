package view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
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
	private TurtleView myTurtleView;
	private ColorPicker myColorPicker;
	private MenuBar myMenuBar;
	private Workspace myWorkspace;
	private static Feed myFeed;

	private Display(Receiver myReceiver) {
		myRoot = new BorderPane();
		myTurtleView = new TurtleView();
		myColorPicker = new ColorPicker();
		myFeed = Feed.getInstance(myReceiver);
		myRoot.setBottom(myFeed);
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
			MenuItem item = new MenuItem(s);
			item.setOnAction(e -> runMethodFromName(s));
			menu.getItems().add(item);
		}
		return menu;
	}

	private void runMethodFromName(String s) {
		s = s.replaceAll(" ", "");
		s = s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toLowerCase());
		try {
			Method m = Display.class.getDeclaredMethod(s, (Class<?>[]) null);
			m.invoke(this, (Object[]) null);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Scene getScene() {
		return this.myScene;
	}

	private void loadCommand() {
		System.out.println("Not Implemented");
	}

	private void saveCommand() {
		System.out.println("Not Implemented");
	}

	private void quit() {
		System.exit(0);
	}

	private void undo() {
		System.out.println("Not Implemented");
	}

	private void redo() {
		System.out.println("Not Implemented");
	}

	private void cut() {
		System.out.println("Not Implemented");
	}

	private void copy() {
		System.out.println("Not Implemented");
	}

	private void paste() {
		System.out.println("Not Implemented");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void chooseBackgroundColor() {
		System.out.println("Not Implemented");
		myRoot.getChildren().add(myColorPicker);
		myColorPicker.setOnAction(new EventHandler() {

			@Override
			public void handle(Event event) {
				myTurtleView.setBackgroundColor(myColorPicker.getValue());
				myRoot.getChildren().remove(myColorPicker);
			}

		});
	}

	private void choosePenColor() {
		System.out.println("Not Implemented");
	}

	private void chooseLanguage() {
		System.out.println("Not Implemented");
	}

	private void showHelp() {
		System.out.println("Not Implemented");
	}

}
