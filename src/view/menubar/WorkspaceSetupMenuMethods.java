package view.menubar;

import java.util.ArrayList;

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
		Display.getInstance().makeWorkspace(null);
	}
}
