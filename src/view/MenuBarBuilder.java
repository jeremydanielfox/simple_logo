package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuBarBuilder {
	private MenuBar myMenuBar;
	private static final String FILE = "File";
	private static final String OPTIONS = "Options";
	private static final String HELP = "Help";

	protected MenuBarBuilder() {
		myMenuBar = new MenuBar();
		setupMenus();
	}

	private void setupMenus() {
		final Menu menu1 = new Menu(FILE);
		final Menu menu2 = new Menu(OPTIONS);
		final Menu menu3 = new Menu(HELP);
		myMenuBar.getMenus().addAll(menu1, menu2, menu3);
	}

	protected MenuBar getMenuBar() {
		return myMenuBar;
	}

}
