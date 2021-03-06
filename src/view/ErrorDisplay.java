package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * A Singleton that displays given errors in a pop-up screen.
 * 
 * @author Jeremy
 *
 */
public class ErrorDisplay {
	private static Stage myStage;
	private static Scene myScene;
	private static Group myGroup;
	private static Label myErrorLabel;

	private static final int WIDTH = 300;
	private static final int HEIGHT = 200;
	private static ErrorDisplay instance;

	private ErrorDisplay() {
		myStage = new Stage();
		myStage.setHeight(HEIGHT);
		myStage.setWidth(WIDTH);
		myGroup = new Group();
		myScene = new Scene(myGroup);
		myErrorLabel = new Label();
		myGroup.getChildren().add(myErrorLabel);
		myStage.setScene(myScene);
		setupDisplay();
	}

	/**
	 * Checks to see if an ErrorDisplay has been created before. If it has, it
	 * returns that one. Otherwise, it creates a new one and returns it.
	 * 
	 * @return
	 */
	protected static ErrorDisplay getInstance() {
		if (instance == null)
			instance = new ErrorDisplay();
		return instance;
	}

	/**
	 * Sets up appropriate qualities of the ErrorDisplay
	 */
	private void setupDisplay() {
		myErrorLabel.setWrapText(true);
		;
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
