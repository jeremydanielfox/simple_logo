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
import model.Model;
import model.Receiver;
import model.database.Database;

public class View implements WorkspaceCreator {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/view");

	private static View instance;
	private Stage myStage;
	private Model myModel;
	private Display myDisplay;

	public View() {
	}

	// remove this getInstance method
	// public View getInstance() {
	// if (instance == null)
	// instance = new View();
	// return instance;
	// }

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
		// CommandSender.setReceiver((Receiver) myModel);
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
		myDisplay.makeWorkspaceDisplay((Receiver) myModel, null);
	}

	public Model getModel() {
		return myModel;
		// }
		//
		// private Database makeDatabase() {
		// ObservableList<String> feed = FXCollections.observableArrayList();
		// ObservableMap<String, String> vars = FXCollections
		// .observableMap(new HashMap<String, String>());
		// vars.addListener(new MapChangeListener<String, String>() {
		//
		// @Override
		// public void onChanged(
		// javafx.collections.MapChangeListener.Change<? extends String, ?
		// extends String> change) {
		// // TODO Auto-generated method stub
		// if (change.wasAdded()) {
		//
		// }
		//
		// }
		//
		// });
		// ObservableMap<String, String> cmds = FXCollections
		// .observableMap(new HashMap<String, String>());
		// return new Database(feed, vars, cmds);
		// }

	}
}
