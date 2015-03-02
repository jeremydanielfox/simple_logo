package view;

import java.util.HashMap;
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
import model.LineData;
import model.Model;
import model.Receiver;
import model.ScreenData;
import model.TurtleData;
import model.database.Database;

public class View {

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
		Scene scene = myDisplay.init(makeDatabase(), myModel);
		myModel.setScreenData(setupScreenData());
//		CommandSender.setReceiver((Receiver) myModel);
		myStage.setScene(scene);
		myStage.show();
	}

	private ObservableList<Drawable> createDrawables(
			ObservableList<Drawable> list, ListChangeListener<Drawable> listener) {
		list.addListener(listener);
				return list;

	}

	private ScreenData setupScreenData() {

		ObservableList<LineData> myLines = FXCollections.observableArrayList();
		myLines.addListener(new ListChangeListener<LineData>() {
			@Override
			public void onChanged(Change<? extends LineData> c) {
				while (c.next()) {
					if (c.getRemovedSize() != 0)
						myDisplay.getSelectedWorkspace().getTV().clearLines();
					for (LineData addItem : c.getAddedSubList()) {
						myDisplay.getSelectedWorkspace().getTV()
								.drawLines(addItem);
					}

				}
			}
		});
		ObservableList<TurtleData> myTurtles = FXCollections
				.observableArrayList();
		myTurtles.addListener(new ListChangeListener<TurtleData>() {
			@Override
			public void onChanged(Change<? extends TurtleData> c) {
				myDisplay.getSelectedWorkspace().getTV().clearTurtles();
				while (c.next()) {
					for (TurtleData addItem : c.getAddedSubList()) {
						myDisplay.getSelectedWorkspace().getTV()
								.drawTurtle(addItem);
					}
				}
			}
		});

		return new ScreenData(myLines, myTurtles);
	}

	public Model getModel() {
		return myModel;
	}

	private Database makeDatabase() {
		ObservableList<String> feed = FXCollections.observableArrayList();
		ObservableMap<String, String> vars = FXCollections
				.observableMap(new HashMap<String, String>());
		vars.addListener(new MapChangeListener<String, String>() {

			@Override
			public void onChanged(
					javafx.collections.MapChangeListener.Change<? extends String, ? extends String> change) {
				// TODO Auto-generated method stub
				if (change.wasAdded()) {

				}

			}

		});
		ObservableMap<String, String> cmds = FXCollections
				.observableMap(new HashMap<String, String>());
		return new Database(feed, vars, cmds);
	}

}
