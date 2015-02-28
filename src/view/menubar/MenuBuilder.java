package view.menubar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBuilder {

	private static final Class<?>[] myParamSetup = new Class<?>[1];
	private MenuBar myMenuBar;
	private ResourceBundle mySource;
	private Class<?> myClass;
	private Object myInstance;
	private ArrayList<Object> myParams;

	public MenuBuilder(MenuBar menuBar, ResourceBundle source, ArrayList<Object> params) {
		myMenuBar = menuBar;
		mySource = source;
		myParams = params;
		myParamSetup[0] = myParams.getClass();
	}

	public MenuBar build() {
		try {
			myClass = Class.forName("view.menubar."
					+ mySource.getString("Name") + "MenuMethods");
			myInstance = myClass.getDeclaredMethod("getInstance",
					(Class<?>[]) null).invoke(null, (Object[]) null);
			myClass.getDeclaredMethod("setParams", myParams.getClass())
					.invoke(myInstance, myParams);
		} catch (ClassNotFoundException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		for (String menu : mySource.getString("Menus").split(", ")) {
			makeMenu(menu);
		}
		return myMenuBar;
	}

	private void makeMenu(String name) {
		Menu menu = getMenu(myMenuBar.getMenus(), name);
		if (mySource.getString(name).isEmpty()) return;
		for (String s : mySource.getString(name).split(", ")) {
			MenuItem item = new MenuItem(s);
			item.setOnAction(e -> runMethodFromName(s));
			menu.getItems().add(item);
		}
	}

	private Menu getMenu(ObservableList<Menu> menus, String name) {
		for (Menu m : menus) {
			if (m.getText().equals(name)) {
				return m;
			}
		}
		Menu menu = new Menu(name);
		myMenuBar.getMenus().add(menu);
		return menu;
	}

	private void runMethodFromName(String s) {
		s = s.replaceAll(" ", "");
		s = s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toLowerCase());
		try {
			Method method = myClass.getMethod(s, (Class<?>[]) null);
			method.invoke(myInstance, (Object[]) null);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
