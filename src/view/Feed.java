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

	private Feed(Receiver receiver) {
		myReceiver = receiver;
		myObjects = new HBox();
		myObjects.setMaxWidth(Double.MAX_VALUE);
		setupPrompter();
		setupAdd();
		setupEnter();
		myObjects.getChildren().addAll(add, prompter, enter);
	}

	/**
	 * 
	 */
	public void setupEnter() {
		enter = new Button(ENTER_TEXT);
		enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (prompter.getText() != null)
					myReceiver.giveText(prompter.getText());
			}
		});
	}

	/**
	 * 
	 */
	public void setupAdd() {
		add = new Button(ADD_TEXT);
	}

	/**
	 * 
	 */
	public void setupPrompter() {
		prompter = new TextField();
		prompter.setPromptText(PROMPT_TEXT);
	}

	protected static Feed getInstance(Receiver receiver) {
		if (instance == null) {
			instance = new Feed(receiver);
			setupFeed();
		}
		return instance;
	}

	private static void setupFeed() {
		instance.getChildren().addAll(myObjects);
	}

}
