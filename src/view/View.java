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

public class View implements WorkspaceCreator {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/view");

	private Stage myStage;
	private Model myModel;
	private Display myDisplay;

	public View() {

	}

	public void init(Stage s) {
		myStage = s;
		myStage.setTitle(myValues.getString("Title"));
		String[] offsetAR = myValues.getString("Initial_Offset").split(", ");
		myModel = new Model(new Point2D(Integer.parseInt(offsetAR[0]),
				Integer.parseInt(offsetAR[1])));
		myModel.setLanguage(myValues.getString("Language"));
		myDisplay = Display.getInstance(getLanguageSetter());
		Scene scene = myDisplay.init(myModel);
		// makeWorkspace();
		makeWorkspace();
		myStage.setScene(scene);
		myStage.show();
	}

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
			hist.getVarsHistory().beRecorded(myDisplay.getVariableHistorian());
		});
	}

	@Override
	public void makeWorkspace() {
		Workspace workspace = new Workspace();
		addListeners(workspace.getTurtles(),workspace.getWorkspaceHistory());
		myDisplay.makeWorkspaceDisplay((Receiver) myModel, workspace.getId());
		myModel.setWorkspace(workspace);
	}

	public LanguageSetter getLanguageSetter() {
		return (LanguageSetter) myModel;
	}


}
