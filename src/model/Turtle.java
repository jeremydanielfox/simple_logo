package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.geometry.Point2D;


public class Turtle {
    private static int ourId = -1; // first new turtle will have id of 0
    
    private Point2D myPosition;
    private Point2D myPreviousPosition;
    private double myHeading;
    private List<LineData> myLines;
    private boolean visible;
    private boolean penUp;

    private int myId;
    private static final Point2D HOME = new Point2D(0, 0);
    private static final Mover MOVER = new UnboundedMover();

    public static void reset () {
          ourId = -1; 
       }
    
    protected Turtle () {
        myId = ourId++;
        myPosition = HOME;
        myPreviousPosition = HOME; //seems sloppy... 
        myHeading = 0;
        myLines = new ArrayList<LineData> ();
        visible = true;
        penUp = false;
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

    public double goHome (Turtle turtle) {
        double r = HOME.distance(turtle.getPosition());
        double theta = turtle.getPosition().angle(HOME);
        move(new PolarVector(r, theta));
        return r;
    }
    
    public int getId () {
        return myId;
    }

    protected void setPosition (Point2D position) {
        myPreviousPosition = new Point2D(myPosition.getX(), myPosition.getY());
        myPosition = position;
    }

    protected void setHeading (double heading) {
        myHeading = heading;
    }
    
    protected void addLine(LineData data){
        myLines.add(data);
    }

    public Point2D getPosition () {
        return myPosition;
    }
    
    protected Point2D getPreviousPosition () {
        return myPreviousPosition;
    }

    public double getHeading () {
        return myHeading;
    }
    
    protected List<LineData> getLineDatas(){
        return Collections.unmodifiableList(myLines);
    }

    protected void toggleVisibility () {
        visible = !visible;
    }

    protected void togglePen () {
        penUp = !penUp;
    }

    public boolean isVisible () {
        return visible;
    }

    protected boolean isPenUp () {
        return penUp;
    }

}
