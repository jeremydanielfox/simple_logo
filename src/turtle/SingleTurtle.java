package turtle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import view.Drawer;
import javafx.geometry.Point2D;
import line.LineList;
import line.SingleLine;
import model.Mover;
import model.PolarVector;
import model.UnboundedMover;


public class SingleTurtle implements Turtle{
    private static int ourId = 0;

    private Point2D myPosition;
    private Point2D myLastPosition;
    private double myHeading;
    private LineList myLines;
    //private List<SingleLine> myLines;
    private boolean visible;
    private boolean penUp;

    private int myId;
    private static Point2D HOME = new Point2D(0, 0);
    private static final Mover MOVER = new UnboundedMover();

    public static void reset () {
        ourId = -1;
    }

    public SingleTurtle () {
    	//HOME = offset;
        myId = ourId++;
        myPosition = HOME;
        myLastPosition = HOME;
        myHeading = 0;
        myLines = new LineList(myId, new ArrayList<SingleLine>());
        visible = true;
        penUp = false;
    }
    
    @Override
    public void beDrawn (Drawer drawer) {
        drawer.drawTurtle(myPosition, myHeading, visible);
    }

    public void move (PolarVector vector) {
        MOVER.moveTurtle(this, vector);
        // update TurtleData, add to list somewhere...
    }

    public double translate (double distance) {
        move(new PolarVector(distance, 0));
        return distance;
    }

    public double rotate (double angle) {
        move(new PolarVector(0, angle));
        return angle;
    }

    public double towards (Point2D target) {
        Point2D deltaVector = myPosition.subtract(target);
        // TODO: must generate cases for each quadrant type... also cases if angle is multiple of 90 deg
        return 0;
    }

    // it shouldn't go through MOVER. algorithm for goHome doesn't change
    public double goHome () {
        double r = HOME.distance(getPosition());
        setHeading(0);
        setPosition(HOME);
        return r;
    }
    
    public double clearScreen ()  {
        myLines.clear();
        return goHome();
    }

    public int getId () {
        return myId;
    }

    protected void setPosition (Point2D position) {
        myLastPosition = new Point2D(myPosition.getX(), myPosition.getY());
        myPosition = position;
    }

    // heading must be between 0 and 360
    protected void setHeading (double heading) {
        if (heading >= 0 && heading < 360) {
            myHeading = heading;
            return;
        }
        if (heading < 0) {
            setHeading(heading + 360);
        }
        else { // heading must be greater than 360
            setHeading(heading - 360);
        }
    }

    protected void addLine (SingleLine data) {
        myLines.add(data);
    }

    public Point2D getPosition () {
        return myPosition;
    }

    protected Point2D getPreviousPosition () {
        return myLastPosition;
    }

    public double getHeading () {
        return myHeading;
    }

    protected LineList getLines () {
        return myLines;
    }

    public double setVisible () {
        visible = true;
        return 1;
    }
    
    public double setInvisible () {
        visible = false;
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

    protected boolean isPenUp () {
        return penUp;
    }
}
