package view.menubar;

import java.io.File;
import java.util.ArrayList;

import model.LanguageSetter;
import javafx.stage.FileChooser;

public class ModelConfigMenuMethods {

	private static ModelConfigMenuMethods instance;
	private LanguageSetter myLangSetter;

	protected static ModelConfigMenuMethods getInstance() {
		if (instance == null)
			instance = new ModelConfigMenuMethods();
		return instance;
	}

	public void setParams(ArrayList<Object> params) {
		myLangSetter = (LanguageSetter) params.get(0);
	}

	public void chooseLanguage() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"Resource files (*.properties)", "*.PROPERTIES");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
		myLangSetter.setLanguage(file.getPath().replaceAll("^.*/src/", "")
				.replaceAll(".properties", ""));
	}

}
