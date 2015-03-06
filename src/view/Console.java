package view;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.writable.Writable;

public class Console implements Historian {
	private TextArea myScreen;
	private static final boolean EDITABLE_VALUE = false;
	
	public void init(double width) {
		myScreen = new TextArea();
		myScreen.setEditable(EDITABLE_VALUE);
		myScreen.setMaxWidth(width);
		HBox.setHgrow(myScreen, Priority.SOMETIMES);
	}

	@Override
	public void record(Map<String, Writable> history) {
		myScreen.clear();
		history.forEach((k,v) -> myScreen.appendText(v.getValue()));

	}
	
	public Node getConsole() {
		return myScreen;
	}

}
