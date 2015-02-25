package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorDisplay {
	private static Stage myStage;
	private static Scene myScene;
	private static Group myGroup;
	private static Label myErrorLabel;

	// private final Popup myPopup = new Popup();
	private static final int WIDTH = 300;
	private static final int HEIGHT = 200;
	private static ErrorDisplay instance;

	// private static Text myText = new Text();

	// private void init() {
	// myPopup.setX(WIDTH);
	// myPopup.setY(HEIGHT);
	// myPopup.getContent().addAll(myText);
	// }
	private ErrorDisplay() {
		myStage = new Stage();
		myStage.setHeight(HEIGHT);
		myStage.setWidth(WIDTH);
		myGroup = new Group();
		myScene = new Scene(myGroup);
		myErrorLabel = new Label();
		myGroup.getChildren().add(myErrorLabel);
		myStage.setScene(myScene);
	}

	protected static ErrorDisplay getInstance() {
		if (instance == null)
			instance = new ErrorDisplay();
		return instance;
	}

	/**
	 * Sets the text of the error label in the pop up window to be that of the
	 * string passed in.
	 * 
	 * @param message
	 *            , the message to be displayed in the window
	 */
	public void setDisplayMessage(String message) {
		myErrorLabel.setText(message);
	}

	/**
	 * Displays the pop-up window
	 */
	public void displayError(Exception e) {
		setDisplayMessage(e.toString());
		myStage.show();
	}

}
