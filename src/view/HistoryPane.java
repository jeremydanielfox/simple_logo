package view;

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
import model.database.Database;

public class HistoryPane {
	private static final int FONT_SIZE = 30;
	private Receiver myReceiver;
	private Database myData;
	private VBox myRoot;
	private ListView<String> myListView;
	private ObservableList<String> myList;

	public HistoryPane(Receiver receiver) {
		myReceiver = receiver;
	}

	public Node init() {
		myData = Database.getInstance();
		myRoot = new VBox();
		HBox titleBox = new HBox();
		Label title = new Label("History");
		title.setFont(new Font(FONT_SIZE));
		Button runButton = new Button("Run");
		runButton.setOnMouseClicked(e -> handleMouseInput());
		runButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		myList = myData.getFeedHistory();
		myListView = new ListView<String>(myList);
		myListView.setPrefHeight(0);
		VBox.setVgrow(myListView, Priority.ALWAYS);
		titleBox.getChildren().addAll(title, runButton);
		myRoot.getChildren().addAll(titleBox, myListView);
		return myRoot;
	}

	private void handleMouseInput() {
		if (myListView.getSelectionModel().getSelectedItem() != null) {
			myReceiver.giveText(myListView.getSelectionModel().getSelectedItem());
			myListView.getSelectionModel().clearSelection();
		}
	}

}
