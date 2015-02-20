package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Display  {
	private static Display instance;
	private Stage myStage;
	private Scene myScene;
	private BorderPane myRoot;
	private static Feed myFeed;
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;

	private Display(Stage stage) {
		myStage = stage;
		myRoot = new BorderPane();
		myRoot.setBottom(myFeed.getInstance());
		myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		myStage.setScene(myScene);
		myStage.show();
	}

	protected static Display getInstance(Stage stage) {
		if (instance == null)
			instance = new Display(stage);
		return instance;
	}
}
