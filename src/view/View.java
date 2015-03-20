package view;

import java.util.ResourceBundle;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.LanguageSetter;
import model.Model;
import model.Receiver;
import model.Workspace;
import model.database.WorkspaceHistory;
import model.turtle.TurtleList;

/**
 * This class is the highest class in the front end, containing the display, as
 * well as the stage. This class also serves as the controller for the program,
 * containing the Model.
 * 
 * @author Jeremy, Peter
 *
 */
public class View implements WorkspaceCreator {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/view");

	private Stage myStage;
	private Model myModel;
	private Display myDisplay;

	/**
	 * Initializes the View by taking in a Stage, initializing all components in
	 * the View and Model, and then showing the stage.
	 * 
	 * @param s
	 */
	public void init(Stage s) {
		myStage = s;
		myStage.setTitle(myValues.getString("Title"));
		String[] offsetAR = myValues.getString("Initial_Offset").split(", ");
		myModel = new Model(new Point2D(Integer.parseInt(offsetAR[0]),
				Integer.parseInt(offsetAR[1])));
		myModel.setLanguage(myValues.getString("Language"));
		myDisplay = new Display(getLanguageSetter(), (WorkspaceCreator) this);
		Scene scene = myDisplay.init(myModel);
		makeWorkspace();
		myStage.setScene(scene);
		myStage.show();

	}

	/**
	 * Adds all necessary listeners to components in the model with appropriate
	 * view components listening to them
	 * 
	 * @param turtles
	 * @param hist
	 */
	private void addListeners(TurtleList turtles, WorkspaceHistory hist) {
		turtles.setChangeListener((obs, ov, nv) -> {
			System.out.println("triggered1");
			myDisplay.getDrawer().clearTurtles();
			turtles.beDrawn(myDisplay.getDrawer());
		});
		turtles.setListChangeListener(c -> {
			System.out.println("triggered2");
			myDisplay.getDrawer().clearLines();
			turtles.drawLines(myDisplay.getDrawer());
		});
		hist.addCmdsListener(c -> {
			hist.getCmdsHistory().beRecorded(myDisplay.getCommandHistorian());
		});
		hist.addFeedListener(c -> {
			System.out.println("Feed Caught");
			hist.getFeedHistory().beRecorded(myDisplay.getHistoryHistorian());
		});
		hist.addVarsListener(c -> {
			System.out.println("Var Caught");
			hist.getVarsHistory().beRecorded(myDisplay.getVariableHistorian());
		});
		hist.addConsoleListener(c -> {
			hist.getConsoleHistory().beRecorded(
					myDisplay.getSelectedWorkspace().getConsole());
		});

	}

	/**
	 * Makes a new Workspace and a new WorkspaceDisplay, passing the same ID to
	 * both of them.
	 */
	@Override
	public void makeWorkspace() {
		Workspace workspace = new Workspace();
		myDisplay.makeWorkspaceDisplay((Receiver) myModel, workspace.getId());
		addListeners(workspace.getTurtles(), workspace.getWorkspaceHistory());
		myModel.setWorkspace(workspace);
	}

	/**
	 * Returns the LanguageSetter
	 * 
	 * @return
	 */
	public LanguageSetter getLanguageSetter() {
		return (LanguageSetter) myModel;
	}

}
