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

	protected Display(LanguageSetter ls,WorkspaceCreator wc) {
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

	private void setupWorkspaces(Receiver receiver) {
		myWorkspaceDisplays = new TabPane();
		myWorkspaceDisplays.setTabMinWidth(Integer.parseInt(myValues.getString("TAB_MIN_WIDTH")));
	}

	public void makeWorkspaceDisplay(Receiver receiver, int id) {
		WorkspaceDisplay myWorkspace = new WorkspaceDisplay(id);
		Node workspaceNode = myWorkspace.init(receiver);
		myWorkspaces.add(myWorkspace);
		Tab tab = new Tab();
		tab.setContent(workspaceNode);
		myWorkspaceDisplays.getTabs().add(tab);
	}

	private void makeMenuBar() throws BadResourcePackageException {
		MenuBuilder menuBuilder = new MenuBuilder(new MenuBar(), this);
		myRoot.setTop(menuBuilder.getMenuBar());
	}

	public BorderPane getRoot() {
		return myRoot;
	}

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
