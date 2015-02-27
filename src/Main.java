import view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the main class.
 */
public class Main extends Application {

	@Override
	public void start(Stage s) {
		View view = View.getInstance();
		view.init(s);
	}

	public static void main(String[] args) {
		launch(args);
	}

}