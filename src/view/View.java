package view;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.LineData;
import model.Model;
import model.Receiver;
import model.ScreenData;
import model.TurtleData;

public class View {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/view");

	private static View instance;

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
		Scene scene = myDisplay.init((Receiver) myModel);
		myModel.setScreenData(setupScreenData());
		CommandSender.setReceiver((Receiver) myModel);
		myStage.setScene(scene);
		myStage.show();
	}

	public static View getInstance() {
		if (instance == null)
			instance = new View();
		return instance;
	}

	private ScreenData setupScreenData() {
		ObservableList<LineData> myLines = FXCollections.observableArrayList();
		myLines.addListener(new ListChangeListener<LineData>() {
			@Override
			public void onChanged(Change<? extends LineData> c) {
				while (c.next()) {
					System.out.println("printing");
					for (LineData addItem : c.getAddedSubList()) {
						myDisplay.getSelectedWorkspace().getTV().drawLines(addItem);
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
						myDisplay.getSelectedWorkspace().getTV().drawTurtle(addItem);
					}
				}
			}
		});

		return new ScreenData(myLines, myTurtles);
	}

	public Model getModel() {
		return myModel;
	}

}
