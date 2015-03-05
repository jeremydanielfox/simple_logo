package view.menubar;

import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.stage.FileChooser;
import view.Display;

public class WorkspaceSetupMenuMethods {
	private static WorkspaceSetupMenuMethods instance;
	private Display myDisplay;

	protected static WorkspaceSetupMenuMethods getInstance() {
		if (instance == null)
			instance = new WorkspaceSetupMenuMethods();
		return instance;
	}

	public void setParams(ArrayList<Object> params) {
		myDisplay = (Display) params.get(0);
	}

	public void newWorkspace() {
		myDisplay.makeWorkspaceDisplay(null,0);
	}
	
	public void loadPresetWorkspace() {
		FileChooser myChooser = new FileChooser();
		File file = myChooser.showOpenDialog(null);
		String path = file.getPath();
		ResourceBundle settings = ResourceBundle.getBundle(path);
	}
}
