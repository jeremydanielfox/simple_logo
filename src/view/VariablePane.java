package view;

import model.database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VariablePane {

	Database myData;
	VBox myRoot;
	ListView<String> myListView;
	ObservableMap<String, String> myMap;
	ObservableList<String> myList;

	public VariablePane() {
		
	}

	public Node init() {
		myData = Database.getInstance();
		myRoot = new VBox();
		HBox titleBox = new HBox();
		Label title = new Label("Variables");
		title.setFont(new Font(30));
		Button editButton = new Button("Edit");
		editButton.setOnMouseClicked(e -> handleMouseInput());
		editButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		myMap = myData.getVarsHistory();
		myList = FXCollections.observableArrayList(myMap.keySet());
		myListView = new ListView<String>(myList);
		myListView.setPrefHeight(0);
		VBox.setVgrow(myListView, Priority.ALWAYS);
		titleBox.getChildren().addAll(title, editButton);
		myRoot.getChildren().addAll(titleBox, myListView);
		return myRoot;
	}

	public void handleMouseInput() {

	}

}
