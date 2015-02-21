package view;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TurtleView {

	private static final ResourceBundle myValues = ResourceBundle.getBundle(
			"view.resources.Values_turtleview", new Locale("en", "US"));
	private Canvas myCanvas;
	private GraphicsContext myGC;
	private StackPane myLayers;
	private Group myTurtles = new Group();
	private Rectangle myBackground;
	private Image turtleImage = new Image("Images/plain-turtle-small.png");
	private TurtleMover myMover;
	private static final int WIDTH = Integer.parseInt(myValues
			.getString("TV_Width"));
	private static final int HEIGHT = Integer.parseInt(myValues
			.getString("TV_Height"));
	private static final int TURTLE_START_X = WIDTH / 2;
	private static final int TURTLE_START_Y = HEIGHT / 2;
	private static final Color BACKGROUND_COLOR = Color.PURPLE;

	protected TurtleView() {
		myCanvas = new Canvas(WIDTH, HEIGHT);
		myGC = myCanvas.getGraphicsContext2D();
		myBackground = new Rectangle(WIDTH, HEIGHT);
		setBackgroundColor(BACKGROUND_COLOR);
		myLayers = new StackPane();
		myLayers.getChildren().addAll(myBackground, myCanvas, myTurtles);
		myMover = (TurtleMover) new NormalMover();
		addTurtle(TURTLE_START_X, TURTLE_START_Y);
	}

	protected void setBackgroundColor(Color color) {
		myBackground.setFill(color);
	}

	protected void addTurtle(int x, int y) {
		Turtle turtle = new Turtle(x, y, turtleImage);
		myTurtles.getChildren().add(turtle);
	}

	protected Node getView() {
		return myLayers;
	}

	protected void moveTurtle(Point2D changepos, int ID) {
		for (Node current : myTurtles.getChildren()) {
			Turtle toMove = (Turtle) current;
			if (toMove.getID() == ID) {
				myMover.moveTurtle(toMove, changepos);
				break;
			}
		}
	}
}
