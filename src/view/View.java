package view;

//import BreadFirstSearch;

import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
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
	private int framesPerSecond = 2500;
	private int NUM_FRAMES_PER_SECOND = 60;

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
//		Timeline animation = new Timeline();
//		//setup the game's loop
//		KeyFrame frame = makeKeyFrame(NUM_FRAMES_PER_SECOND);
//		
//		animation.setCycleCount(Animation.INDEFINITE);
//		animation.getKeyFrames().add(frame);
//		animation.play();
	}
//	private KeyFrame makeKeyFrame(int frameRate) {
//		return new KeyFrame(Duration.millis(framesPerSecond / frameRate),
//				e -> updateSim());
//	}
	
	

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
					if (c.getRemovedSize()!=0) 
						myDisplay.getSelectedWorkspace().getTV().clearLines();
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
