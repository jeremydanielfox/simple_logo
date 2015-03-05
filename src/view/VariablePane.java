package view;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Receiver;
import model.writable.Writable;

public class VariablePane implements DataPane {

	private Receiver myReceiver;
	private BorderPane myRoot;
	private VBox myVBox;
	private ListView<String> myListView;
	private ObservableList<String> myList;
	private Stage myStage;
	private int myID;
	private Feed myFeed;

	public VariablePane(Receiver receiver, int id, Feed feed) {
		myReceiver = receiver;
		myID = id;
		myFeed = feed;
	}

	public Node init() {
		myVBox = new VBox();
		HBox titleBox = new HBox();
		Label title = new Label("Variables");
		title.setFont(new Font(30));
		Button editButton = new Button("Edit");
		editButton.setOnMouseClicked(e -> handleEditInput(myListView
				.getSelectionModel().getSelectedItem()));
		editButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		Button addButton = new Button("Add");
		addButton.setOnMouseClicked(e -> handleAddInput(myListView
				.getSelectionModel().getSelectedItem()));
		addButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		myList = FXCollections.observableArrayList(myMap.keySet());
		myListView = new ListView<String>(myList);
		myListView.setPrefHeight(0);
		VBox.setVgrow(myListView, Priority.ALWAYS);
		titleBox.getChildren().addAll(title, editButton, addButton);
		myVBox.getChildren().addAll(titleBox, myListView);
		return myVBox;
	}

	public void handleEditInput(String name) {
		myStage = new Stage();
		myStage.setHeight(200);
		myStage.setWidth(300);
		VBox myRoot = new VBox();
		Label myVarName = new Label(name);
		TextField myVarText = new TextField(myMap.get(name));
		Button doneButton = new Button("Done");
		doneButton.setOnMouseClicked(e -> handleEditOutput(name,
				myVarText.getText()));
		myRoot.getChildren().addAll(myVarName, myVarText, doneButton);
		Scene myScene = new Scene(myRoot);
		myStage.setScene(myScene);
		myStage.show();
	}

	private void handleEditOutput(String name, String value) {
		myReceiver.giveText("set " + name + " " + value, myID);
		myMap.put(name, value);
		myStage.close();
	}

	private void handleAddInput(String var) {
		myFeed.addText(var);
	}

	public void put(String key, String value) {
		myMap.put(key, value);
	}

	@Override
	public void record(Map<String, Writable> history) {
		history.forEach((k, v) -> myMap.put(k, v.getValue()));

	}

}
