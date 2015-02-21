package view;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Receiver;

public class View extends Application {
	private Stage myStage;
	private Receiver myReceiver;
	private ResourceBundle myResources;
	private Display myDisplay;
	private String myResourcesLocation = "resources.languages/English";

	@Override
	public void start(Stage s) throws Exception {
		myStage = s;
		myResources = ResourceBundle.getBundle(myResourcesLocation);
		myDisplay.getInstance(myStage, myReceiver);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
