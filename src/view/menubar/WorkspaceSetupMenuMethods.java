package view.menubar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.stage.FileChooser;
import model.Receiver;
import view.Display;
import view.WorkspaceCreator;

public class WorkspaceSetupMenuMethods {
	private static WorkspaceSetupMenuMethods instance;
	private Display myDisplay;
	private WorkspaceCreator myCreator;

	protected static WorkspaceSetupMenuMethods getInstance() {
		if (instance == null)
			instance = new WorkspaceSetupMenuMethods();
		return instance;
	}

	public void setParams(ArrayList<Object> params) {
		myDisplay = (Display) params.get(0);
		myCreator = (WorkspaceCreator) params.get(1);
	}

	public void newWorkspace() {
		myCreator.makeWorkspace();
//		myDisplay.makeWorkspaceDisplay(null,0);
	}
	
	public void loadPresetWorkspace() {
		FileChooser myChooser = new FileChooser();
		File file = myChooser.showOpenDialog(null);
		String path = file.getPath();
		ResourceBundle settings = ResourceBundle.getBundle(path);
	}
	public void loadCommand() throws FileNotFoundException {
		FileChooser myChooser = new FileChooser();
		File file = myChooser.showOpenDialog(null);
		String content = new Scanner(file).useDelimiter("\\Z").next();
		myDisplay.getReceiver().giveText(content,myDisplay.getSelectedWorkspace().getID());
	}
}
