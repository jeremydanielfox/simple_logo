package model.turtle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import model.line.LineList;
import model.line.SingleLine;
import model.turtle.mover.Mover;
import model.turtle.mover.UnboundedMover;
import view.Drawer;


/**
 * Represents a single turtle in the simulation. Turtles can be moved in a variety of ways. They
 * also have access to their visiblity and whether or not the pen is up or down for them.
 * 
 * @author Nathan Prabhu
 *
 */
public class SingleTurtle implements Turtle {

    private Point2D myPosition;
    private Point2D myLastPosition;
    private double myHeading;
    private LineList myLines;
    private boolean visible;
    private boolean penUp;
    private int myId;

    private ObjectProperty<Point2D> myPositionProperty = new SimpleObjectProperty<Point2D>();
    private DoubleProperty myHeadingProperty = new SimpleDoubleProperty();
    private BooleanProperty myVisiblityProperty = new SimpleBooleanProperty();

    private static final Point2D HOME = new Point2D(0, 0);
    private static final Mover MOVER = new UnboundedMover();

    public SingleTurtle (int id) {
        myId = id;
        setPosition(HOME);
        setHeading(0);
        show();
        myLines = new LineList();
        penUp = false;
    }

    @Override
    public void beDrawn (Drawer drawer) {
        drawer.drawTurtle(myPosition, myHeading, myId);
    }

    public void move (PolarVector vector) {
        MOVER.moveTurtle(this, vector);
        // update TurtleData, add to list somewhere...
    }

    public void translate (double distance) {
        move(new PolarVector(distance, 0));
    }

    public double rotate (double angle) {
        move(new PolarVector(0, angle));
        return angle;
    }

    public double towards (Point2D target) {
        Point2D deltaVector = myPosition.subtract(target);
        // TODO: must generate cases for each quadrant type... also cases if angle is multiple of 90
        // deg
        return 0;
    }

    // it shouldn't go through MOVER. algorithm for goHome doesn't change
    public double goHome () {
        double r = HOME.distance(getPosition());
        setHeading(0);
        setPosition(HOME);
        move(new PolarVector(0, 0));
        return r;
    }

    public double clearScreen () {
        myLines.clear();
        return goHome();
    }

    public int getId () {
        return myId;
    }

    public void setPosition (Point2D position) {
        // myPosition will be null first time through
        Point2D currentPosition = (myPosition == null) ? HOME : myPosition;

        myLastPosition = new Point2D(currentPosition.getX(), currentPosition.getY());
        myPosition = position;
        myPositionProperty.setValue(position);
    }

    // heading must be between 0 and 360
    public double setHeading (double heading) {
        if (heading >= 0 && heading < 360) {
            myHeading = heading;
            myHeadingProperty.setValue(heading);
            return heading;
        }
        if (heading < 0) {
            return setHeading(heading + 360);
        }
        else {
            return setHeading(heading - 360);
        }
    }

    public void addLine (SingleLine data) {
        myLines.add(data);
    }

    public Point2D getPosition () {
        return myPosition;
    }

    public Point2D getLastPosition () {
        return myLastPosition;
    }

    public double getHeading () {
        return myHeading;
    }

    public LineList getLines () {
        return myLines;
    }

    public double show () {
        visible = true;
        myVisiblityProperty.set(true);
        return 1;
    }

    public double hide () {
        visible = false;
        myVisiblityProperty.set(false);
        return 0;
    }

    public double setPenUp () {
        penUp = true;
        return 1;
    }

    public double setPenDown () {
        penUp = false;
        return 0;
    }

    public boolean isVisible () {
        return visible;
    }

    public boolean isPenUp () {
        return penUp;
    }

    public ObjectProperty<Point2D> getPositionProperty () {
        return myPositionProperty;
    }

    public DoubleProperty getHeadingProperty () {
        return myHeadingProperty;
    }

    public BooleanProperty getVisibilityProperty () {
        return myVisiblityProperty;
    }
}
