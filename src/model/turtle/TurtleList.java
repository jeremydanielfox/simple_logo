package model.turtle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.geometry.Point2D;
import model.Clearable;
import model.node.CommandList;
import view.Clearer;
import view.Drawer;


// one per workspace
public class TurtleList implements Turtle, Clearable {

    private int myId;
    private Map<Integer, SingleTurtle> allTurtlesMap;
    private Map<Integer, SingleTurtle> activeTurtlesMap;
    private List<Integer> activeIds;

    private ChangeListener myTurtleListener;
    private ListChangeListener myLineListener;

    // private ObservableList<SingleTurtle> allTurtles;

    public TurtleList (int id) {
        myId = id;

        // initialize maps
        allTurtlesMap = new HashMap<Integer, SingleTurtle>();
        allTurtlesMap.put(1, new SingleTurtle(1));
        activeTurtlesMap = allTurtlesMap;
        //activeIds = new ArrayList<Integer>();
        //activeIds.add(1);
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
            if (!(allTurtlesMap.containsKey(id))) {
                addNewTurtle(id);
            }
            activeTurtlesMap.put(id, allTurtlesMap.get(id));
        }
    }
    
    public double ask (List<Integer> queried, CommandList commands){
        List<Integer> currentlyActive = new ArrayList<Integer>();
        currentlyActive.addAll(activeTurtlesMap.keySet());
        setActive(queried);
        double result = commands.evaluate();
        setActive(currentlyActive);
        return result;
    }
    

    public Collection<SingleTurtle> getAllTurtles () {
        return allTurtlesMap.values();
    }

    public void drawLines (Drawer drawer) {
        getAllTurtles().stream().map(SingleTurtle::getLines)
                .forEach(linelist -> linelist.beDrawn(drawer));
    }

    private void addNewTurtle (int id) {
        SingleTurtle turtle = new SingleTurtle(id);
        allTurtlesMap.put(id, turtle);
        addChangeListener(turtle);
        addListChangeListener(turtle);
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
        myTurtleListener = listener;
        // will add change listener to first turtle
        addChangeListener(allTurtlesMap.get(1));
    }

    public void setListChangeListener (ListChangeListener listener) {
        myLineListener = listener;
        // will add listchange listener to first turtle
        addListChangeListener(allTurtlesMap.get(1));
    }

    private void addChangeListener (SingleTurtle turtle) {
        turtle.getHeadingProperty().addListener(myTurtleListener);
        turtle.getPositionProperty().addListener(myTurtleListener);
        turtle.getVisibilityProperty().addListener(myTurtleListener);
        turtle.getLineListProperty().addListener(myTurtleListener);
    }

    private void addListChangeListener (SingleTurtle turtle) {
        turtle.getLines().addListener(myLineListener);
    }

    public void addLocationListener (InvalidationListener listener) {
        allTurtlesMap.values().forEach(turtle -> turtle.addLocationListener(listener));
    }

    public void addHeadingListener (InvalidationListener listener) {
        allTurtlesMap.values().forEach(turtle -> turtle.addHeadingListener(listener));
    }

}
