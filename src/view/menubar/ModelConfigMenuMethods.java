package view.menubar;

import java.io.File;
import java.util.ArrayList;

import javafx.stage.FileChooser;
import view.View;

public class ModelConfigMenuMethods {

	private static ModelConfigMenuMethods instance;
	private View myView;

	protected static ModelConfigMenuMethods getInstance() {
		if (instance == null)
			instance = new ModelConfigMenuMethods();
		return instance;
	}

	public void setParams(ArrayList<Object> params) {
		myView = (View) params.get(0);
	}

	public void chooseLanguage() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"Resource files (*.properties)", "*.PROPERTIES");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
		myView.getLanguageSetter().setLanguage(
				file.getPath().replaceAll("^.*/src/", "")
						.replaceAll(".properties", ""));
	}

}
