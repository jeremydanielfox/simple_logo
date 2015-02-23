package view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

public class HistoryPane {

	ScrollPane myRoot;

	public HistoryPane() {
	}

	public Node init() {
		myRoot = new ScrollPane();
		return myRoot;
	}

	public void handleMouseInput(MouseEvent e) {

	}

}
