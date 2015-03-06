package view;

import java.util.Map;

import model.writable.Writable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CommandPane implements DataPane, Historian {

	private Feed myFeed;

	public CommandPane(Feed feed) {
		myFeed = feed;
	}

	@Override
	public Node init() {
		VBox myRoot = new VBox();
		HBox myTitleBox = new HBox();
		Label myTitle = new Label("Commands");
		Button myAddButton = new Button("Add");
		ObservableList<String> myList = FXCollections.observableArrayList(myMap
				.keySet());
		ListView<String> myListView = new ListView<String>(myList);
		myListView.setPrefHeight(0);
		VBox.setVgrow(myListView, Priority.ALWAYS);
		myAddButton.setOnMouseClicked(e2 -> {
			myFeed.addText(myListView.getSelectionModel().getSelectedItem());
			myFeed.getStage().close();
		});
		myTitleBox.getChildren().addAll(myTitle, myAddButton);
		myRoot.getChildren().addAll(myTitleBox, myListView);
		return myRoot;
	}
	
	@Override
	public void record(Map<String, Writable> history) {
		history.forEach((k, v) -> myMap.put(k, v.getValue()));

	}

}
