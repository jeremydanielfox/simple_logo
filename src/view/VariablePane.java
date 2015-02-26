package view;

import model.database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VariablePane {

	Database myData;
	VBox myRoot;
	ListView<String> myListView;
	ObservableMap<String, String> myMap;
	ObservableList<String> myList;
	Stage myStage;

	public VariablePane() {
		
	}

	public Node init() {
		myData = Database.getInstance();
		myRoot = new VBox();
		HBox titleBox = new HBox();
		Label title = new Label("Variables");
		title.setFont(new Font(30));
		Button editButton = new Button("Edit");
		editButton.setOnMouseClicked(e -> handleEditInput(myListView.getSelectionModel().getSelectedItem()));
		editButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		Button addButton = new Button("Add");
		addButton.setOnMouseClicked(e -> handleAddInput(myListView.getSelectionModel().getSelectedItem()));
		addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		myMap = myData.getVarsHistory();
		myMap.put(":length", "50");
		myList = FXCollections.observableArrayList(myMap.keySet());
		myListView = new ListView<String>(myList);
		myListView.setPrefHeight(0);
		VBox.setVgrow(myListView, Priority.ALWAYS);
		titleBox.getChildren().addAll(title, editButton, addButton);
		myRoot.getChildren().addAll(titleBox, myListView);
		return myRoot;
	}

	public void handleEditInput(String name) {
		myStage = new Stage();
		myStage.setHeight(200);
		myStage.setWidth(300);
		VBox myRoot = new VBox();
		Label myVarName = new Label(name);
		TextField myVarText = new TextField(myMap.get(name));
		Button doneButton = new Button("Done");
		doneButton.setOnMouseClicked(e -> handleEditOutput(name, myVarText.getText()));
		myRoot.getChildren().addAll(myVarName, myVarText, doneButton);
		Scene myScene = new Scene(myRoot);
		myStage.setScene(myScene);
		myStage.show();
	}
	
	private void handleEditOutput(String name, String value) {
		myMap.put(name, value);
		myStage.close();
	}

	private void handleAddInput(String var) {
		Feed.addText(var);
	}

}
