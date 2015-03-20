package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.LanguageSetter;
import model.Model;
import model.Receiver;
import view.menubar.MenuBuilder;
import Exceptions.BadResourcePackageException;

/**
 * Contains everything seen by the user, including all Workspaces and the
 * MenuBar
 * 
 * @author Jeremy
 *
 */
public class Display {
	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/display");

	private Scene myScene;
	private BorderPane myRoot;
	private LanguageSetter myLangSetter;
	private WorkspaceCreator myCreator;
	private TabPane myWorkspaceDisplays;
	private Collection<WorkspaceDisplay> myWorkspaces;
	private Model myModel;

	/**
	 * Takes in two backend-connected interfaces (the LanguageSetter and the
	 * WorkspaceCreator) and uses them to create the Display
	 * 
	 * @param ls
	 * @param wc
	 */
	protected Display(LanguageSetter ls, WorkspaceCreator wc) {
		myRoot = new BorderPane();
		myWorkspaces = new ArrayList<>();
		myLangSetter = ls;
		myCreator = wc;
	}

	public Display getDisplay() {
		return this;
	}

	public LanguageSetter getLangSetter() {
		return this.myLangSetter;
	}

	public WorkspaceCreator getWorkspaceCreator() {
		return this.myCreator;
	}

	/**
	 * Instantiates the display by creating the menubar and the workspace
	 * collection
	 * 
	 * @param model
	 * @return
	 */
	public Scene init(Model model) {
		myModel = model;
		setupWorkspaces((Receiver) myModel);
		try {
			makeMenuBar();
		} catch (BadResourcePackageException e) {
			e.printStackTrace();
		}
		myRoot.setCenter(myWorkspaceDisplays);

		myScene = new Scene(myRoot, Integer.parseInt(myValues
				.getString("Width")), Integer.parseInt(myValues
				.getString("Height")));

		return this.myScene;
	}

	/**
	 * Sets up a TabPane of Workspaces connected to a Receiver that can take
	 * inputs from the Display
	 * 
	 * @param receiver
	 */
	private void setupWorkspaces(Receiver receiver) {
		myWorkspaceDisplays = new TabPane();
		myWorkspaceDisplays.setTabMinWidth(Integer.parseInt(myValues
				.getString("TAB_MIN_WIDTH")));
	}

	/**
	 * Creates a new WorkspaceDisplay linked to the receiver and with its own ID
	 * 
	 * @param receiver
	 * @param id
	 */
	public void makeWorkspaceDisplay(Receiver receiver, int id) {
		WorkspaceDisplay myWorkspace = new WorkspaceDisplay(id);
		Node workspaceNode = myWorkspace.init(receiver);
		myWorkspaces.add(myWorkspace);
		Tab tab = new Tab();
		tab.setContent(workspaceNode);
		myWorkspaceDisplays.getTabs().add(tab);
	}

	/**
	 * Creates the MenuBar and sets it at the top of the Display
	 * 
	 * @throws BadResourcePackageException
	 */
	private void makeMenuBar() throws BadResourcePackageException {
		MenuBuilder menuBuilder = new MenuBuilder(new MenuBar(), this);
		myRoot.setTop(menuBuilder.getMenuBar());
	}

	/**
	 * Returns the root node for the Display
	 * 
	 * @return
	 */
	public BorderPane getRoot() {
		return myRoot;
	}

	/**
	 * Returns the Workspace the user currently has selected. This is the
	 * Workspace that is in front of the rest.
	 * 
	 * @return
	 */
	public WorkspaceDisplay getSelectedWorkspace() {
		for (Tab currentTab : myWorkspaceDisplays.getTabs()) {
			if (currentTab.isSelected())
				for (WorkspaceDisplay currentWS : myWorkspaces) {
					if (currentWS.getRoot().equals(currentTab.getContent()))
						return currentWS;
				}
		}
		return null;
	}

	public Drawer getDrawer() {
		return getSelectedWorkspace().getDrawer();
	}

	public Historian getCommandHistorian() {
		return getSelectedWorkspace().getCommandPane();
	}

	public Historian getVariableHistorian() {
		return getSelectedWorkspace().getVariablePane();
	}

	public Historian getHistoryHistorian() {
		return getSelectedWorkspace().getHistoryPane();
	}

	public Receiver getReceiver() {
		return (Receiver) myModel;
	}
}
