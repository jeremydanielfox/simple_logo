package model.turtle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import model.Clearable;
import view.Clearer;
import view.Drawer;


// one per workspace
public class TurtleList implements Turtle, Clearable {
    private static int ourId;

    private int myId;
    private Map<Integer, SingleTurtle> allTurtlesMap;
    private Map<Integer, SingleTurtle> activeTurtlesMap;

    private ChangeListener myListener;

    // private ObservableList<SingleTurtle> allTurtles;

    public TurtleList (int id) {
        myId = id;

        // initialize maps
        allTurtlesMap = new HashMap<Integer, SingleTurtle>();
        allTurtlesMap.put(1, new SingleTurtle(1));
        activeTurtlesMap = allTurtlesMap;
    }

    @Override
    public void beDrawn (Drawer drawer) {
        allTurtlesMap.values().stream().filter(SingleTurtle::isVisible)
                .forEach(turtle -> turtle.beDrawn(drawer));
    }

    public void beCleared (Clearer clearer) {
        clearScreen();
        clearer.clearTurtles();
    }

    public void setActive (List<Integer> idList) {
        activeTurtlesMap = new HashMap<Integer, SingleTurtle>();
        for (int id : idList) {
            if (allTurtlesMap.containsKey(id)) {
                activeTurtlesMap.put(id, allTurtlesMap.get(id));
            }
            else {
                addNewTurtle(id);
            }
        }
    }

    public Collection<SingleTurtle> getAllTurtles () {
        return allTurtlesMap.values();
    }

    private void addNewTurtle (int id) {
        SingleTurtle turtle = new SingleTurtle(id);
        activeTurtlesMap.put(id, turtle);
        allTurtlesMap.put(id, turtle);
        addChangeListener(turtle);
    }

    // TODO: use matchers to filter turtle ids with given list of ids
    // public void remove (int... ids){
    // myList.stream().filter(this:: filterID);
    // }
    //
    // private boolean filterId (int[] ids) {
    //
    // }

    @Override
    public void translate (double distance) {
        activeTurtlesMap.values().forEach(turtle -> turtle.translate(distance));
    }

    @Override
    public double rotate (double angle) {
        activeTurtlesMap.values().forEach(turtle -> turtle.rotate(angle));
        return angle;
    }

    @Override
    public double towards (Point2D target) {
        // TODO Auto-generated method stub
        return 0;
    }

    // TODO: fix return value
    @Override
    public double goHome () {
        activeTurtlesMap.values().forEach(turtle -> turtle.goHome());
        return 0;
    }

    @Override
    public double show () {
        activeTurtlesMap.values().forEach(turtle -> turtle.show());
        return 1;
    }

    @Override
    public double hide () {
        activeTurtlesMap.values().forEach(turtle -> turtle.hide());
        return 1;
    }

    @Override
    public double setPenUp () {
        activeTurtlesMap.values().forEach(turtle -> turtle.setPenUp());
        return 1;
    }

    @Override
    public double setPenDown () {
        activeTurtlesMap.values().forEach(turtle -> turtle.setPenDown());
        return 0;
    }

    @Override
    public double clearScreen () {
        activeTurtlesMap.values().forEach(turtle -> turtle.clearScreen());
        return 0;
    }

    public int getId () {
        return myId;
    }

    public void setChangeListener (ChangeListener listener) {
        myListener = listener;
        // will add change listener to first turtle
        addChangeListener(allTurtlesMap.get(1));
    }

    private void addChangeListener (SingleTurtle turtle) {
        turtle.getHeadingProperty().addListener(myListener);
        turtle.getPositionProperty().addListener(myListener);
        turtle.getVisibilityProperty().addListener(myListener);
    }

    public void addLocationListener (InvalidationListener listener) {
        allTurtlesMap.values().forEach(turtle -> turtle.addLocationListener(listener));
    }

    public void addHeadingListener (InvalidationListener listener) {
        allTurtlesMap.values().forEach(turtle -> turtle.addHeadingListener(listener));
    }

}
