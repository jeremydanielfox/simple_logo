package view.menubar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import view.menubar.DefaultMenuMethods;

public class MenuBuilder {

	MenuBar myMenuBar;
	ResourceBundle myResources;
	String mySource;
//	Object myMethodClass;

	public MenuBuilder(MenuBar menuBar, String source) {
		myMenuBar = menuBar;
		myResources = ResourceBundle.getBundle("resources/menus/"+source);
		mySource = source;
	}

	public MenuBar build() {
		for (String menu : myResources.getString("Menus").split(", ")) {
			myMenuBar.getMenus().add(makeMenu(menu));
		}
		return myMenuBar;
	}
	
	private Menu makeMenu(String name) {
		Menu menu = new Menu(name);
		for (String s : myResources.getString(name).split(", ")) {
			MenuItem item = new MenuItem(s);
			item.setOnAction(e -> runMethodFromName(s));
			menu.getItems().add(item);
		}
		return menu;
	}
	
	private void runMethodFromName(String s) {
		s = s.replaceAll(" ", "");
		s = s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toLowerCase());
		try {
			DefaultMenuMethods methodClass = DefaultMenuMethods.getInstance();
//			myMethodClass = Class.forName("view.menubar."+mySource+"MenuMethods");
			
			Method method = Class.forName("view.menubar."+mySource+"MenuMethods").getDeclaredMethod(s, (Class<?>[]) null);
			method.invoke(methodClass, (Object[]) null);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException  | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
