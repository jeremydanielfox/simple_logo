package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.Receiver;

public class Feed extends Parent {
	private static Receiver myReceiver;
	private static Feed instance;
	private static HBox myObjects;
	private static Button add;
	private static Button enter;
	private static TextField prompter;
	private static final String PROMPT_TEXT = "Input command here";
	private static final String ADD_TEXT = "Add";
	private static final String ENTER_TEXT = "Return";

	private Feed() {
		myObjects = new HBox();
		prompter = new TextField();
		prompter.setPromptText(PROMPT_TEXT);
		add = new Button(ADD_TEXT);
		enter = new Button(ENTER_TEXT);
		enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (prompter.getText() != null)
					myReceiver.giveText(prompter.getText());
			}
		});
		myObjects.getChildren().addAll(add, prompter, enter);
	}

	protected static Feed getInstance(Receiver receiver) {
		if (instance == null) {
			instance = new Feed();
			myReceiver = receiver;
			setupFeed();
		}
		return instance;
	}

	private static void setupFeed() {
		instance.getChildren().addAll(myObjects);
	}

}
