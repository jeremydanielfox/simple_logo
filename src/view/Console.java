package view;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.writable.Writable;

/**
 * Provides a console that reflects outputs from commands run. Updates every
 * time a command is run.
 * 
 * @author Jeremy
 *
 */
public class Console implements Historian {
	private TextArea myScreen;
	private static final boolean EDITABLE_VALUE = false;

	/**
	 * Initializes the console, creating an appropriately sized TextArea
	 * 
	 * @param width
	 */
	public void init(double width) {
		myScreen = new TextArea();
		myScreen.setEditable(EDITABLE_VALUE);
		myScreen.setMaxWidth(width);
		HBox.setHgrow(myScreen, Priority.SOMETIMES);
	}

	/**
	 * Takes in history in the form of a Map, and adds the history value to the
	 * console display
	 */
	@Override
	public void record(Map<String, Writable> history) {
		myScreen.clear();
		history.forEach((k, v) -> myScreen.appendText(v.getValue()));

	}

	/**
	 * Returns the console
	 * 
	 * @return
	 */
	public Node getConsole() {
		return myScreen;
	}

}
