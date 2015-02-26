package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Receiver;

public class Feed {
	private Receiver myReceiver;
	private HBox myObjects;
	private VBox myButtons;
	private Button add;
	private Button enter;
	private TextArea prompter;
	private static final String PROMPT_TEXT = "Input command here";
	private static final String ADD_TEXT = "Add";
	private static final String ENTER_TEXT = "Return";
	private static final double PROMPT_WIDTH = Double.MAX_VALUE;

	protected Feed(Receiver receiver) {
		myReceiver = receiver;
		myObjects = new HBox();
		myButtons = new VBox();
		setupPrompter();
		setupAdd();
		setupEnter();
		myButtons.getChildren().addAll(enter, add);
		myObjects.getChildren().addAll(prompter, myButtons);
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
					// CommandSender.send(prompter.getText());
					try {
						myReceiver.giveText(prompter.getText());
						} catch (Exception ex) {
							ErrorDisplay.getInstance().displayError(ex);
						}
					myReceiver.giveText(prompter.getText());
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
		HBox.setHgrow(prompter, Priority.ALWAYS);
	}

	protected HBox getFeed() {
		return myObjects;
	}

}
