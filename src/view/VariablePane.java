package view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

public class VariablePane {

	ScrollPane myRoot;

	public VariablePane() {
		myRoot = new ScrollPane();
	}

	public Node init() {
		return myRoot;
	}

	public void handleMouseInput(MouseEvent e) {

	}

}
