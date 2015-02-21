package view;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
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
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;

	private Display(Stage stage, Receiver myReceiver) {
		myStage = stage;
		myRoot = new BorderPane();
		myRoot.setBottom(myFeed.getInstance(myReceiver));
		setupMenuBar();
		myWorkspace = new Workspace();
		myRoot.setCenter(myWorkspace);
		myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		myStage.setScene(myScene);
		myStage.show();
	}

	protected static Display getInstance(Stage stage, Receiver myReceiver) {
		if (instance == null)
			instance = new Display(stage, myReceiver);
		return instance;
	}

	private void setupMenuBar() {
		MenuBarBuilder myMBBuilder = new MenuBarBuilder();
		myMenuBar = myMBBuilder.getMenuBar();
		myRoot.setTop(myMenuBar);
	}
}
