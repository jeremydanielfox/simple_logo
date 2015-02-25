package view;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import model.Receiver;
import model.ScreenData;
import model.Turtle;

public class View {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/view");

	private Stage myStage;
	private Model myModel;
	private static Display myDisplay;

	public View(Stage s) {
		myStage = s;
		myModel = new Model(setupScreenData());
		myModel.setLanguage(myValues.getString("Language"));
	}

	public void init() {
		myStage.setTitle(myValues.getString("Title"));
		myDisplay = Display.getInstance((Receiver) myModel);
		Scene scene = myDisplay.getScene();
		CommandSender.setReceiver((Receiver) myModel);
		myStage.setScene(scene);
		myStage.show();
	}
	
	private ScreenData setupScreenData() {
		return new ScreenData(new Turtle());
	}

}
