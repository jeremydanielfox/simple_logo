package view;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TurtleView {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/turtleview");
	private Canvas myCanvas;
	private GraphicsContext myGC;
	private StackPane myLayers;
	private Group myTurtles = new Group();
	private Rectangle myBackground;
	private Image turtleImage = new Image("images/plain-turtle-small.png");
	private static final int WIDTH = Integer.parseInt(myValues
			.getString("Width"));
	private static final int HEIGHT = Integer.parseInt(myValues
			.getString("Height"));
	private static final int TURTLE_START_X = WIDTH / 2;
	private static final int TURTLE_START_Y = HEIGHT / 2;
	private static final Color BACKGROUND_COLOR = Color.PURPLE;

	public TurtleView() {
		myCanvas = new Canvas(WIDTH, HEIGHT);
		myGC = myCanvas.getGraphicsContext2D();
		myBackground = new Rectangle(WIDTH, HEIGHT);
		setBackgroundColor(BACKGROUND_COLOR);
		myLayers = new StackPane();
		myLayers.getChildren().addAll(myBackground, myCanvas, myTurtles);
		//myMover = (Mover) new UnboundedMover();
		//addTurtle(TURTLE_START_X, TURTLE_START_Y);
	}

	public void setBackgroundColor(Color color) {
		myBackground.setFill(color);
	}

	protected void addTurtle(int x, int y) {
		TurtleImage turtle = new TurtleImage(x, y, turtleImage);
		myTurtles.getChildren().add(turtle);
	}

	public Node getView() {
		return myLayers;
	}

	// protected void moveTurtle(Point2D changepos, int ID) {
	// for (Node current : myTurtles.getChildren()) {
	// TurtleImage toMove = (TurtleImage) current;
	// if (toMove.getID() == ID) {
	// myMover.moveTurtle(toMove, changepos);
	// break;
	// }
	// }
	
	public void setTurtleImage(Image img) {
		turtleImage = img;
	}
}
