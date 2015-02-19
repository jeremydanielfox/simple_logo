package view;

import java.util.Locale;
import java.util.ResourceBundle;

import display.Display;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {
	
	private static final ResourceBundle myValues = ResourceBundle.getBundle("resources/display/values", new Locale("view")); 
	
	Stage myStage;
//	Model myModel;
	Display myDisplay;
	
	public View (Stage s) {
		myStage = s;
//		myModel = new Model();
	}

	public void init() {
		myStage.setTitle(myValues.getString("Title"));
        myDisplay = new Display();
        Scene scene = myDisplay.getScene();
        myStage.setScene(scene);
        myStage.show();
	}

}
