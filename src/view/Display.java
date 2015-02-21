package view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Receiver;

public class Display {
	private static Display instance;
	private Stage myStage;
	private Scene myScene;
	private BorderPane myRoot;
	private MenuBar myMenuBar;
	private Workspace myWorkspace;
	private static Feed myFeed;
	private static final ResourceBundle myValues = ResourceBundle.getBundle(
			"view.resources.Values_display", new Locale("en", "US"));

	private Display(Stage stage, Receiver myReceiver) {
		myStage = stage;
		myRoot = new BorderPane();
		myRoot.setBottom(myFeed.getInstance(myReceiver));
		myRoot.setTop(makeMenuBar());
		// setupMenuBar();
		myRoot.setCenter(makeWorkspace());
		myScene = new Scene(myRoot, Integer.parseInt(myValues
				.getString("Width")), Integer.parseInt(myValues
				.getString("Height")));
		myStage.setScene(myScene);
		myStage.show();
	}

	protected static Display getInstance(Stage stage, Receiver myReceiver) {
		if (instance == null)
			instance = new Display(stage, myReceiver);
		return instance;
	}

	//
	// private void setupMenuBar() {
	// MenuBarBuilder myMBBuilder = new MenuBarBuilder();
	// myMenuBar = myMBBuilder.getMenuBar();
	// myRoot.setTop(myMenuBar);
	// }

	private Node makeMenuBar() {

		MenuBar menuBar = new MenuBar();
		try {
			menuBar.getMenus().add(makeMenu("File"));
			menuBar.getMenus().add(makeMenu("Edit"));
			menuBar.getMenus().add(makeMenu("View"));
			menuBar.getMenus().add(makeMenu("Options"));
			menuBar.getMenus().add(makeMenu("Help"));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return menuBar;
	}

	private Menu makeMenu(String name) throws NoSuchMethodException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Menu menu = new Menu(name);
		String[] arrayCharles = myValues.getString(name).split(", ");
		for (String s : arrayCharles) {
			MenuItem item = new MenuItem();
			Method m = Display.class.getDeclaredMethod(getMethodName(s));
			// not sure why I'm getting this error
//			 item.setOnAction(e -> m.invoke(null, null));
			menu.getItems().add(item);
		}
		return menu;
	}

	private String getMethodName(String s) {
		s.replaceAll(" ", "");
		String first = String.valueOf(s.charAt(0));
		s.replaceFirst("%c", first.toLowerCase());
		return s;
	}

	public Scene getScene() {
		return this.myScene;
	}


	private Workspace makeWorkspace() {
		return new Workspace();
	}
}
