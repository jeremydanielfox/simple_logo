import view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the main class.
 */
public class Main extends Application {

	@Override
	public void start(Stage s) {
		View view = new View(s);
		view.init();
	}

	public static void main(String[] args) {
		launch(args);
	}

}