package view;

import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Receiver;
import model.writable.Writable;

public class PalletPane extends DataPane implements Historian {

	private Receiver myReceiver;
	private Stage myStage;
	private int myID;
	private Feed myFeed;

	public PalletPane(Receiver receiver, int id, Feed feed) {
		myReceiver = receiver;
		myID = id;
		myFeed = feed;
	}

	public void handleEdit(String name) {
		myStage = new Stage();
		myStage.setMaxHeight(Double.MAX_VALUE);
		myStage.setMaxWidth(Double.MAX_VALUE);
		VBox root = new VBox();
		Label myColorName = new Label(name);
		ColorPicker myColorPicker = new ColorPicker();

		root.getChildren().addAll(myColorName, myColorPicker);
		Scene myScene = new Scene(root);
		myStage.setScene(myScene);
		myStage.show();
		myColorPicker.show();
		myColorPicker.setOnAction(e -> handleEditOutput(name,
				myColorPicker.getValue()));
	}

	private void handleEditOutput(String name, Color value) {
		myReceiver.giveText(String.format("setpallette " + name + " %f %f %f", value.getRed(), value.getGreen(), value.getBlue()), myID);
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
