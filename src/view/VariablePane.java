package view;

import java.util.Map;

import model.database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VariablePane {

	VBox myRoot;
	ListView<String> myListView;
	Map<String, String> myMap;
	ObservableList<String> myList;

	public VariablePane() {
		
	}

	public Node init() {
		myRoot = new VBox();
		Label title = new Label("Variables");
		title.setFont(new Font(30));
//		myMap = Database.getViewerVarsMap();
		myList = FXCollections.observableArrayList(myMap.keySet());
		myListView = new ListView<String>(myList);
		myListView.setPrefHeight(0);
		VBox.setVgrow(myListView, Priority.ALWAYS);
		myRoot.getChildren().addAll(title, myListView);
		return myRoot;
	}

	public void handleMouseInput(MouseEvent e) {

	}

}
