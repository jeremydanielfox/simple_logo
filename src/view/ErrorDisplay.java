package view;

import javafx.scene.text.Text;
import javafx.stage.Popup;

public class ErrorDisplay {
	private final Popup myPopup = new Popup();
	private final static int WIDTH = 300;
	private static final int HEIGHT = 200;
	private static Text myText = new Text();

	public void displayError(Exception e) {
		myPopup.show(owner);
	}

	private void init() {
		myPopup.setX(WIDTH);
		myPopup.setY(HEIGHT);
		myPopup.getContent().addAll(myText);
	}
}
