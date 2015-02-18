package view;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Feed extends Parent {
	private static Feed instance;
	private static HBox myObjects;
	private static Button add;
	private static Button enter;
	private static TextField prompter;
	private static final String PROMPT_TEXT = "Input command here";

	private Feed() {
		myObjects = new HBox();
		add = new Button();
		enter = new Button();
		prompter = new TextField();
		prompter.setPromptText(PROMPT_TEXT);
		myObjects.getChildren().addAll(add, prompter, enter);
	}

	protected static Feed getInstance() {
		if (instance == null) {
			instance = new Feed();
			setupFeed();
		}
		return instance;
	}

	private static void setupFeed() {
		instance.getChildren().addAll(myObjects);
	}
}
