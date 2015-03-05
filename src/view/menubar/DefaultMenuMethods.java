package view.menubar;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public final class DefaultMenuMethods {

	private static DefaultMenuMethods instance;

	protected static DefaultMenuMethods getInstance() {
		if (instance == null)
			instance = new DefaultMenuMethods();
		return instance;
	}
	
	public void setParams(ArrayList<Object> params) {
		return;
	}

	public void loadCommand() throws FileNotFoundException {
		String content = new Scanner(new File("Slogo_Functions")).useDelimiter("\\Z").next();
		System.out.println(content);
		//TODO: pass this to backend
	}

	public void saveCommand() {
		System.out.println("Not Implemented");
	}

	public void quit() {
		System.exit(0);
	}

	public void undo() {
		System.out.println("Not Implemented");
	}

	public void redo() {
		System.out.println("Not Implemented");
	}

	public void cut() {
		System.out.println("Not Implemented");
	}

	public void copy() {
		System.out.println("Not Implemented");
	}

	public void paste() {
		System.out.println("Not Implemented");
	}

	public void showHelp() {
		File htmlFile = new File("help.html");
		try {
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (java.io.IOException e) {
			System.out.println("error caught");
			try {
				Desktop.getDesktop().open(htmlFile);
			} catch (java.io.IOException e1) {
				System.out.println("really caught");
			}
		}
	}

}
