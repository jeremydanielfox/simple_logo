package view;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.Receiver;
import Exceptions.SlogoException;

/**
 * Contains a TextArea the user can type in and a Receiver that can receive the
 * text in the TextArea. This provides the functionality by which the user can
 * send commands to the backend.
 * 
 * @author Jeremy
 *
 */
public class Feed {

	private Receiver myReceiver;
	private HBox myObjects;
	private Button enter;
	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/feed");
	private TextArea prompter;
	private int myID;

	private static final String PROMPT_TEXT = myValues.getString("Prompt_Text");
	private static final String ENTER_TEXT = myValues.getString("Enter_Text");

	protected Feed(Receiver receiver, int id) {
		myID = id;
		myReceiver = receiver;
		myObjects = new HBox();
		setupPrompter();
		setupEnter();
		myObjects.getChildren().addAll(prompter, enter);
	}

	/**
	 * Creates a button labeled with the appropriate text and sets the button to
	 * give text from the TextArea to the Receiver. If an error is thrown in the
	 * backend, it is caught here and sent to the ErrorDisplay.
	 */
	public void setupEnter() {
		enter = new Button(ENTER_TEXT);
		enter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		enter.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String text = prompter.getText();
				if (text != null && !text.equals(""))
					try {
						myReceiver.giveText(text, myID);
					} catch (SlogoException ex) {
						ErrorDisplay.getInstance().displayError(ex);
					}
				System.out.println(prompter.getText().toString());
				prompter.clear();
			}
		});
	}

	/**
	 * Sets up the TextArea that the user types in
	 */
	public void setupPrompter() {
		prompter = new TextArea();
		prompter.setPromptText(PROMPT_TEXT);
		HBox.setHgrow(prompter, Priority.ALWAYS);
	}

	/**
	 * Returns the HBox containing the TextArea and the Enter button
	 * 
	 * @return
	 */
	protected HBox getFeed() {
		return myObjects;
	}

	/**
	 * Allows for text to be inserted into the TextArea at the point the cursor
	 * is at.
	 * 
	 * @param text
	 */
	public void addText(String text) {
		prompter.insertText(prompter.getCaretPosition(), text);
	}

}
