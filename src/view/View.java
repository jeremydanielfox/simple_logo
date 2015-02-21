package view;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import model.Receiver;

public class View {
	private Stage myStage;
	private Model myModel;
	private static final ResourceBundle myValues = ResourceBundle.getBundle(
			"view.resources.Values_view", new Locale("en", "US"));
	private Display myDisplay;
	private String myResourcesLocation = "resources.languages/English";

	public View(Stage s) {
		myStage = s;
		myModel = new Model();
	}

	// @Override
	// public void start(Stage s) throws Exception {
	// myStage = s;
	// myDisplay.getInstance(myStage, (Receiver) myModel);
	// }

	// public static void main(String[] args) {
	// launch(args);
	// }

	public void init() {
		myStage.setTitle(myValues.getString("Title"));
		myDisplay =  myDisplay.getInstance(myStage, (Receiver) myModel);
		Scene scene = myDisplay.getScene();
		myStage.setScene(scene);
		myStage.show();
	}

}
