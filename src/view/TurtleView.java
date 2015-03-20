// This entire file is part of my masterpiece
// Jeremy Fox
package view;

import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import model.Drawable;
import model.line.SingleLine;

/**
 * Provides the window in which the user can see all drawn objects, including
 * turtles, lines, and stamps.
 * 
 * @author Jeremy, Peter
 *
 */
public class TurtleView implements Drawer, Configurable {

	private static final int OFFSET_FACTOR = 20;
	private static final ResourceBundle myValues = ResourceBundle
			.getBundle("resources/values/turtleview");
	private Canvas myLineCanvas;
	private Canvas myTurtleCanvas;
	private GraphicsContext myLineGC;
	private GraphicsContext myTurtleGC;
	private StackPane myLayers;
	private Rectangle myBackground;
	private DoubleProperty myXOffset = new SimpleDoubleProperty();
	private DoubleProperty myYOffset = new SimpleDoubleProperty();
	private Image turtleImage = new Image("images/plain-turtle-small.png",
			TURTLE_WIDTH, TURTLE_HEIGHT, true, true);
	private static final int TURTLE_WIDTH = Integer.parseInt(myValues
			.getString("TurtleWidth"));
	private static final int TURTLE_HEIGHT = Integer.parseInt(myValues
			.getString("TurtleHeight"));
	private int turtleXOffset = TURTLE_WIDTH / 2;
	private int turtleYOffset = TURTLE_HEIGHT / 2;
	private static final double LINE_WIDTH = 2;
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Color PEN_COLOR = Color.PINK;

	/**
	 * Creates a new TurtleView that is bound to the window size, so that as the
	 * user adjusts the size of the window, the TurtleView scales appropriately
	 */
	public TurtleView() {
		myLineCanvas = new Canvas();
		myTurtleCanvas = new Canvas();
		setupGraphicsContext();
		myBackground = new Rectangle();
		setBackgroundColor(BACKGROUND_COLOR);
		myLayers = new StackPane();
		myLayers.getChildren().addAll(myBackground, myLineCanvas,
				myTurtleCanvas);
		myXOffset.bind(myTurtleCanvas.widthProperty().divide(2));
		myYOffset.bind(myTurtleCanvas.heightProperty().divide(2));
		myLineCanvas.widthProperty()
				.bind(myLayers.widthProperty().subtract(OFFSET_FACTOR));
		myLineCanvas.heightProperty().bind(
				myLayers.heightProperty().subtract(OFFSET_FACTOR));
		myTurtleCanvas.widthProperty().bind(
				myLayers.widthProperty().subtract(OFFSET_FACTOR));
		myTurtleCanvas.heightProperty().bind(
				myLayers.heightProperty().subtract(OFFSET_FACTOR));
		myLineCanvas.widthProperty().addListener(observable -> redraw());
		myLineCanvas.heightProperty().addListener(observable -> redraw());
		myTurtleCanvas.widthProperty().addListener(observable -> redraw());
		myTurtleCanvas.heightProperty().addListener(observable -> redraw());
	}

	/**
	 * Returns the GraphicsContext associated with the Canvas the Turtles are
	 * located on
	 * 
	 * @return
	 */
	public GraphicsContext getTurtleGraphicsContext() {
		return myTurtleGC;
	}

	/**
	 * Returns the GraphicsContext associated with the Canvas the Lines are
	 * located on
	 * 
	 * @return
	 */
	public GraphicsContext getLineGraphicsContext() {
		return myLineGC;
	}

	/**
	 * Returns the Canvas the Turtles are drawn on
	 * 
	 * @return
	 */
	public Canvas getTurtleCanvas() {
		return myTurtleCanvas;
	}

	/**
	 * Allows for the canvas to be resized if the user adjusts the size of the
	 * window
	 */
	private void redraw() {
		myBackground.setHeight(myLineCanvas.getHeight());
		myBackground.setWidth(myLineCanvas.getWidth());
	}

	/**
	 * Initializes the appropriate GraphicsContexts, setting appropriate colors
	 * and strokes
	 */
	private void setupGraphicsContext() {
		myLineGC = myLineCanvas.getGraphicsContext2D();
		myLineGC.setStroke(PEN_COLOR);
		myTurtleGC = myTurtleCanvas.getGraphicsContext2D();
		setPenWidth(LINE_WIDTH);
	}

	/**
	 * Sets the Pen color by setting the stroke in the Line GraphicsContext
	 */
	public void setPenColor(Color color) {
		myLineGC.setStroke(color);
	}

	/**
	 * Sets the Pen Width by adjusting line width in the Line GraphicsContext
	 */
	public void setPenWidth(double width) {
		myLineGC.setLineWidth(width);
	}

	/**
	 * Sets the background color by coloring the rectangle (background) located
	 * behind the canvases
	 */
	public void setBackgroundColor(Color color) {
		myBackground.setFill(color);
	}

	/**
	 * Returns the stackpane containing all viewable objects
	 */
	public Node getView() {
		return myLayers;
	}

	/**
	 * Sets the instance variable associated with the Turtle image to the image
	 * the is given
	 */
	public void setTurtleImage(Image img) {
		turtleImage = img;
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
	public void drawRotatedTurtle(GraphicsContext gc, double angle,
			double tlpx, double tlpy) {
		Image image = turtleImage;
		gc.save(); // saves the current state on stack, including the current
		// transform
		rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight()
				/ 2);
		gc.drawImage(image, tlpx, tlpy, TURTLE_WIDTH, TURTLE_HEIGHT);
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

	/**
	 * Erases all onscreen turtles by clearing a rectangle on the turtle Canvas
	 * that is the size of the canvas.
	 */
	@Override
	public void clearTurtles() {
		myTurtleGC.clearRect(0, 0, myTurtleCanvas.getWidth(),
				myTurtleCanvas.getHeight());
	}

	/**
	 * Erases all onscreen lines by clearing a rectangle on the Line Canvas that
	 * is the size of the canvas
	 */
	@Override
	public void clearLines() {
		myLineGC.clearRect(0, 0, myTurtleCanvas.getWidth(),
				myTurtleCanvas.getHeight());
	}

	/**
	 * Draws a line from a given start to a given end on the Line Canvas
	 */
	@Override
	public void drawLine(Point2D start, Point2D end) {
		myLineGC.strokeLine(start.getX() + myXOffset.get() + turtleXOffset,
				start.getY() + turtleYOffset + myYOffset.get(), end.getX()
						+ turtleXOffset + myXOffset.get(), end.getY()
						+ myYOffset.get() + turtleYOffset);
	}

	/**
	 * Draws a turtle at a given location and rotated at a given heading
	 */
	@Override
	public void drawTurtle(Point2D location, double heading) {
		Image image = turtleImage;
		myTurtleGC.save(); // saves the current state on stack, including
							// the current transform
		rotate(myTurtleGC, heading,
				location.getX() + myXOffset.get() + image.getWidth() / 2,
				location.getY() + myYOffset.get() + image.getHeight() / 2);
		myTurtleGC.drawImage(image, location.getX() + myXOffset.get(),
				location.getY() + myYOffset.get(), TURTLE_WIDTH, TURTLE_HEIGHT);
		myTurtleGC.restore(); // back to original state (before rotation)

	}

}
