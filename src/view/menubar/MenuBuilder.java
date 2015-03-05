package view.menubar;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.ResourceBundle;

import view.Display;
import Exceptions.BadResourcePackageException;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBuilder {

	private MenuBar myMenuBar;
	private Display myDisplay;
	private ResourceBundle mySource;
	private Class<?> myClass;
	private Object myInstance;
	private ArrayList<Object> myParams;

	public MenuBuilder(MenuBar menuBar, Display display) {
		myMenuBar = menuBar;
		myDisplay = display;
		File dir = new File("src/resources/menus/");
		File[] dirArray = dir.listFiles();
		if (dirArray != null) {
			for (File file : dirArray) {
				String sourceName = file.getName()
						.replaceAll(".properties", "");
				mySource = ResourceBundle.getBundle("resources/menus/"
						+ sourceName);
				ArrayList<Object> params = new ArrayList<Object>();
				if (mySource.containsKey("Params")) {
					for (String s : mySource.getString("Params").split(", ")) {
						try {
							Object o = myDisplay
									.getClass()
									.getDeclaredMethod("get" + s,
											(Class<?>[]) null)
									.invoke(myDisplay, (Object[]) null);
							params.add(o);
						} catch (IllegalAccessException
								| IllegalArgumentException
								| InvocationTargetException
								| NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						}
					}
				}
				myMenuBar = build(params);
			}
		} else {
			throw new BadResourcePackageException();
		}

	}

	public MenuBar getMenuBar() {
		return myMenuBar;
	}

	private MenuBar build(ArrayList<Object> params) {
		myParams = params;
		try {
			myClass = Class.forName("view.menubar."
					+ mySource.getString("Name") + "MenuMethods");
			myInstance = myClass.getDeclaredMethod("getInstance",
					(Class<?>[]) null).invoke(null, (Object[]) null);
			myClass.getDeclaredMethod("setParams", myParams.getClass()).invoke(
					myInstance, myParams);
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
		Class<?> classy = myClass;
		Object instance = myInstance;
		if (mySource.getString(name).isEmpty())
			return;
		for (String s : mySource.getString(name).split(", ")) {
			MenuItem item = new MenuItem(s);
			item.setOnAction(e -> runMethodFromName(s, classy, instance));
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

	private void runMethodFromName(String s, Class<?> classy, Object instance) {
		s = s.replaceAll(" ", "");
		s = s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toLowerCase());
		try {
			Method method = classy.getMethod(s, (Class<?>[]) null);
			method.invoke(instance, (Object[]) null);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
