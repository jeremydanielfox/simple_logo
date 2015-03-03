package view;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Drawable;
import model.LanguageSetter;
import model.Model;
import model.Receiver;
<<<<<<< HEAD
=======
import model.database.Database;
>>>>>>> c2dd9120c89819b6bbe3e862106d68a8e3c98f53

public class View implements WorkspaceCreator {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/view");

	private Stage myStage;
	private Model myModel;
	private Display myDisplay;

	public View() {
	}

	public void init(Stage s) {
		myStage = s;
		myStage.setTitle(myValues.getString("Title"));
		String[] offsetAR = myValues.getString("Initial_Offset").split(", ");
		myModel = new Model(new Point2D(Integer.parseInt(offsetAR[0]),
				Integer.parseInt(offsetAR[1])));
		myModel.setLanguage(myValues.getString("Language"));
		myDisplay = Display.getInstance();
		Scene scene = myDisplay.init(myModel);
		makeWorkspace();
		myStage.setScene(scene);
		myStage.show();
	}

	private ObservableList<Drawable> createDrawables() {
		ObservableList<Drawable> list = FXCollections.observableArrayList();
		list.addListener((ListChangeListener.Change<? extends Drawable> c) -> {
			while (c.next()) {
				for (Drawable removeItem : c.getRemoved()) {
					removeItem.Clear(myDisplay.getSelectedWorkspace()
							.getTurtleView());
					break;
				}
				for (Drawable addItem : c.getAddedSubList()) {
					addItem.Draw(myDisplay.getSelectedWorkspace()
							.getTurtleView());
				}
			}
		});
		return list;

	}

	public void makeWorkspace() {
		String[] drawNames = new String[] { "Lines", "Turtles", "Stamps" };
		Map<String, ObservableList<Drawable>> myDrawables = new HashMap<>();
		for (int i = 0; i < drawNames.length; i++)
			myDrawables.put(drawNames[i], createDrawables());
		ObservableList<String> promptHist = FXCollections.observableArrayList();
		promptHist
				.addListener((ListChangeListener.Change<? extends String> c) -> {
					while (c.next()) {
						for (String addItem : c.getAddedSubList()) {
							myDisplay.getSelectedWorkspace().getHistoryPane()
									.add(addItem);
						}
					}
				});
		ObservableMap<String, String> varsMap = FXCollections
				.observableHashMap();
		varsMap.addListener((
				MapChangeListener.Change<? extends String, ? extends String> c) -> {
			myDisplay.getSelectedWorkspace().getVariablePane()
					.put(c.getKey(), c.getValueAdded());
		});
		myModel.initializeNewWorkspace(myDrawables);
		myDisplay.makeWorkspaceDisplay((Receiver) myModel);
	}

	public LanguageSetter getLanguageSetter() {
		return (LanguageSetter) myModel;
	}
}
