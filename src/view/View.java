package view;

import java.util.ResourceBundle;

import model.Model;
import model.Receiver;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/view");

	private Stage myStage;
	private Model myModel;
	private static Display myDisplay;

	public View(Stage s) {
		myStage = s;
		myModel = new Model();
		myModel.setLanguage(myValues.getString("Language"));
	}

	public void init() {
		myStage.setTitle(myValues.getString("Title"));
		myDisplay = Display.getInstance((Receiver) myModel);
		Scene scene = myDisplay.getScene();
		myStage.setScene(scene);
		myStage.show();
	}

}
