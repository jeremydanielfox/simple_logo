package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.Receiver;

public class Feed {
	private static Receiver myReceiver;
	private static Feed instance;
	private static HBox myObjects;
	private static Button add;
	private static Button enter;
	private static TextArea prompter;
	private static final String PROMPT_TEXT = "Input command here";
	private static final String ADD_TEXT = "Add";
	private static final String ENTER_TEXT = "Return";
	private static final double PROMPT_WIDTH = Double.MAX_VALUE;

	private Feed(Receiver receiver) {
		myReceiver = receiver;
		myObjects = new HBox();
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
					CommandSender.send(prompter.getText());
				// myReceiver.giveText(prompter.getText());
				System.out.println(prompter.getText().toString());
				prompter.clear();
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
		prompter = new TextArea();
		prompter.setPromptText(PROMPT_TEXT);
//		prompter.setPrefWidth(0); 
		HBox.setHgrow(prompter, Priority.ALWAYS);
	}

	protected static Feed getInstance(Receiver receiver) {
		if (instance == null) {
			instance = new Feed(receiver);
		}
		return instance;
	}

	//
	// private static void setupFeed() {
	// instance.getChildren().addAll(myObjects);
	// }

	protected HBox getFeed() {
		return myObjects;
	}

}
