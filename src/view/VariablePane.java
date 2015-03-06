package view;

import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Receiver;
import model.writable.Writable;

public class VariablePane extends DataPane implements Historian {

	private Receiver myReceiver;
	private Stage myStage;
	private int myID;
	private Feed myFeed;

	public VariablePane(Receiver receiver, int id, Feed feed) {
		myReceiver = receiver;
		myID = id;
		myFeed = feed;
	}

	public void handleEdit(String name) {
		myStage = new Stage();
		myStage.setHeight(200);
		myStage.setWidth(300);
		VBox myRoot = new VBox();
		Label myVarName = new Label(name);
		TextField myVarText = new TextField(super.getMap().get(name));
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
		myStage.close();
	}

	public void handleAdd(String var) {
		myFeed.addText(var);
	}

	@Override
	public void record(Map<String, Writable> history) {
		history.forEach((k, v) -> super.getMap().put(k, v.getValue()));
	}

}
