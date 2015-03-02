package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.LineData;
import model.TurtleData;

public class TurtleView {

	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/turtleview");
	private Canvas myLineCanvas;
	private Canvas myTurtleCanvas;
	private GraphicsContext myLineGC;
	private GraphicsContext myTurtleGC;
	private StackPane myLayers;
	private Group myTurtlesGroup = new Group();
	private Collection<TurtleImage> myTurtlesList = new ArrayList<>();
	private Rectangle myBackground;
	private Image turtleImage = new Image("images/plain-turtle-small.png",
			TURTLE_WIDTH, TURTLE_HEIGHT, true, true);
	// private static final int WIDTH = Integer.parseInt(myValues
	// .getString("Width"));
	// private static final int HEIGHT = Integer.parseInt(myValues
	// .getString("Height"));
	private static final int TURTLE_WIDTH = Integer.parseInt(myValues
			.getString("TurtleWidth"));
	private static final int TURTLE_HEIGHT = Integer.parseInt(myValues
			.getString("TurtleHeight"));
	private static final double LINE_WIDTH = 2;
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Color PEN_COLOR = Color.PINK;

	public TurtleView() {
		myLineCanvas = new Canvas();
		myTurtleCanvas = new Canvas();
		setupGraphicsContext();
		myBackground = new Rectangle();
		setBackgroundColor(BACKGROUND_COLOR);
		myLayers = new StackPane();
		myLayers.getChildren().addAll(myBackground, myLineCanvas,
				myTurtleCanvas,myTurtlesGroup);

		myLineCanvas.widthProperty()
				.bind(myLayers.widthProperty().subtract(20));
		myLineCanvas.heightProperty().bind(
				myLayers.heightProperty().subtract(20));
		myTurtleCanvas.widthProperty().bind(
				myLayers.widthProperty().subtract(20));
		myTurtleCanvas.heightProperty().bind(
				myLayers.heightProperty().subtract(20));
		myLineCanvas.widthProperty().addListener(observable -> redraw());
		myLineCanvas.heightProperty().addListener(observable -> redraw());
		myTurtleCanvas.widthProperty().addListener(observable -> redraw());
		myTurtleCanvas.heightProperty().addListener(observable -> redraw());
	}

	private void redraw() {
		myBackground.setHeight(myLineCanvas.getHeight());
		myBackground.setWidth(myLineCanvas.getWidth());
	}

	private void setupGraphicsContext() {
		myLineGC = myLineCanvas.getGraphicsContext2D();
		myLineGC.setStroke(PEN_COLOR);
		myTurtleGC = myTurtleCanvas.getGraphicsContext2D();
		setPenWidth(LINE_WIDTH);
	}

	public void setPenColor(Color color) {
		myLineGC.setStroke(color);
	}

	public void setPenWidth(double width) {
		myLineGC.setLineWidth(width);
	}

	public void setBackgroundColor(Color color) {
		myBackground.setFill(color);
	}

	public Node getView() {
		return myLayers;
	}

	public void setTurtleImage(Image img) {
		turtleImage = img;
	}

	public void drawLines(LineData current) {
		myLineGC.strokeLine(current.getStart().getX(), current.getStart()
				.getY(), current.getFinish().getX(), current.getFinish().getY());
	}

	public void drawTurtle(TurtleData currentData) {
		drawRotatedImage(myTurtleGC, turtleImage, currentData.getHeading(),
				currentData.getX(), currentData.getY());
//		TurtleImage inList = getTurtleImage(currentData);
//		
//		if (inList==null) {
//			TurtleImage temp = new TurtleImage(currentData.getX(),
//					currentData.getY(), currentData.getHeading(), turtleImage);
//			myTurtlesGroup.getChildren().add(temp);
//			myTurtlesList.add(temp);
//		}
//		else {
//			System.out.println(inList.getX());
//			inList.setX(inList.getX()-100);
//			System.out.println(inList.getX());
//////			inList.setX(inList.getX()+currentData.getX());
//////			inList.setY(inList.getY()+currentData.getY());
////			TranslateTransition tt = new TranslateTransition(Duration.seconds(3),inList);
////			tt.setFromX(inList.getX());
////			tt.setFromY(inList.getY());
////			tt.setToX(inList.getX()+currentData.getX());
////			tt.setToY(inList.getY()+currentData.getY());
//////			tt.setByX(currentData.getX());
//////			tt.setByY(currentData.getY());
////			tt.setCycleCount(Timeline.INDEFINITE);
////			tt.play();
//////			inList.setTranslateX(currentData.getX());
//////			inList.setTranslateY(currentData.getY());
//////			inList.setRotate(currentData.getHeading());
//		}
	}
	
	private TurtleImage getTurtleImage(TurtleData data) {
		for (TurtleImage current: myTurtlesList) {
			if (current.getID() == data.getID())
				return current;
		}
		return null;
	}

	public void drawRotatedTurtle() {

	}

	/**
	 * Draws an image on a graphics context.
	 *
	 * The image is drawn at (tlpx, tlpy) rotated by angle pivoted around the
	 * point: (tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2)
	 *
	 * @param gc
	 *            the graphics context the image is to be drawn on.
	 * @param angle
	 *            the angle of rotation.
	 * @param tlpx
	 *            the top left x coordinate where the image will be plotted (in
	 *            canvas coordinates).
	 * @param tlpy
	 *            the top left y coordinate where the image will be plotted (in
	 *            canvas coordinates).
	 */
	private void drawRotatedImage(GraphicsContext gc, Image image,
			double angle, double tlpx, double tlpy) {
		gc.save(); // saves the current state on stack, including the current
					// transform
		rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight()
				/ 2);
		gc.drawImage(image, tlpx, tlpy);
		gc.restore(); // back to original state (before rotation)
	}

	/**
	 * Sets the transform for the GraphicsContext to rotate around a pivot
	 * point.
	 *
	 * @param gc
	 *            the graphics context the transform to applied to.
	 * @param angle
	 *            the angle of rotation.
	 * @param px
	 *            the x pivot coordinate for the rotation (in canvas
	 *            coordinates).
	 * @param py
	 *            the y pivot coordinate for the rotation (in canvas
	 *            coordinates).
	 */
	private void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(),
				r.getTx(), r.getTy());
	}

	public void clearTurtles() {
		myTurtleGC.clearRect(0, 0, myTurtleCanvas.getWidth(),
				myTurtleCanvas.getHeight());
	}
	
	public void clearLines() {
		myLineGC.clearRect(0, 0, myTurtleCanvas.getWidth(),
				myTurtleCanvas.getHeight());
	}
}
