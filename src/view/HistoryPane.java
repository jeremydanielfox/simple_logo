package view;

//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
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
	Database myData;
	VBox myRoot;
	ListView<String> myListView;
	ObservableList<String> myList;

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
		// Used to send command on selection, but couldn't repeat select cause
		// there was no "change" for the ChangeListener
		// myListView.getSelectionModel().selectedItemProperty()
		// .addListener(new ChangeListener<String>() {
		// public void changed(ObservableValue<? extends String> ov,
		// String old_val, String new_val) {
		// myListView.getSelectionModel().clearSelection();
		// CommandSender.send(new_val);
		//
		// }
		// });
		VBox.setVgrow(myListView, Priority.ALWAYS);
		titleBox.getChildren().addAll(title, runButton);
		myRoot.getChildren().addAll(titleBox, myListView);
		return myRoot;
	}

	private void handleMouseInput() {
		if (myListView.getSelectionModel().getSelectedItem() != null) {
			myReceiver.giveText(myListView.getSelectionModel().getSelectedItem());
//			CommandSender
//					.send(myListView.getSelectionModel().getSelectedItem());
			myListView.getSelectionModel().clearSelection();
		}
	}

}
