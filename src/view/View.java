package view;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/view");

	private Stage myStage;
	private Model myModel;
	private Display myDisplay;

	public View(Stage s) {
		myStage = s;
		myModel = new Model();
	}

	public void init() {
		myStage.setTitle(myValues.getString("Title"));
		myDisplay = new Display();
		Scene scene = myDisplay.getScene();
		myStage.setScene(scene);
		myStage.show();
	}

}
