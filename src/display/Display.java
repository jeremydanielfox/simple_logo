package display;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;

public class Display {
	
	private static final ResourceBundle myValues = ResourceBundle.getBundle("resources/display/values", new Locale("display")); 
	
	Scene myScene;
	BorderPane myRoot;
	
	public Display () {
		myRoot = new BorderPane();
		
		myRoot.setTop(makeMenuBar());
		
		myScene = new Scene(myRoot, Integer.parseInt(myValues.getString("Width")), Integer.parseInt(myValues.getString("Height")));
	}
	
	private Node makeMenuBar() {
		Menu fileMenu = new Menu(myValues.getString("File"));
		Display.class.getDeclaredMethod(name, parameterTypes)
		return null;
	}

	public Scene getScene() {
		return this.myScene;
	}
}
