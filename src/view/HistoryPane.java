package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Receiver;

public class HistoryPane {
	private static final int FONT_SIZE = 30;
	private Receiver myReceiver;
	private VBox myRoot;
	private int myID;
	private ListView<String> myListView;
	private ObservableList<String> myList;

	public HistoryPane(Receiver receiver,int id) {
		myReceiver = receiver;
		myID = id;
	}


	public Node init() {
		// myData = Database.getInstance();
		myRoot = new VBox();
		myList = FXCollections.observableArrayList();
		HBox titleBox = new HBox();
		Label title = new Label("History");
		title.setFont(new Font(FONT_SIZE));
		Button runButton = new Button("Run");
		runButton.setOnMouseClicked(e -> handleMouseInput());
		runButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		myListView = new ListView<String>(myList);
		myListView.setPrefHeight(0);
		VBox.setVgrow(myListView, Priority.ALWAYS);
		titleBox.getChildren().addAll(title, runButton);
		myRoot.getChildren().addAll(titleBox, myListView);
		return myRoot;
	}

	private void handleMouseInput() {
		if (myListView.getSelectionModel().getSelectedItem() != null) {
			myReceiver.giveText(myListView.getSelectionModel()
					.getSelectedItem(),myID);
			myListView.getSelectionModel().clearSelection();
		}
	}

	public void add(String str) {
		myList.add(str);
	}

}
