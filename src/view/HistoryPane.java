package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class HistoryPane {

	VBox myRoot;
	ListView<String> myListView;
	ObservableList<String> myList;

	public HistoryPane() {
		
	}

	public Node init() {
		myRoot = new VBox();
		Label title = new Label("History");
		title.setFont(new Font(30));
		myList = FXCollections.observableArrayList();
		myListView = new ListView<String>(myList);
		myListView.setPrefHeight(0);
		VBox.setVgrow(myListView, Priority.ALWAYS);
		myRoot.getChildren().addAll(title, myListView);
		return myRoot;
	}

	public void handleMouseInput(MouseEvent e) {

	}
	
}
