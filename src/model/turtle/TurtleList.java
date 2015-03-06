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
import model.node.CommandList;
import view.Drawer;


// one per workspace
public class TurtleList implements Turtle {

    private int myId;
    private Map<Integer, SingleTurtle> allTurtlesMap;
    private Map<Integer, SingleTurtle> activeTurtlesMap;
    private int myCurrentId;

    private ChangeListener myTurtleListener;
    private ListChangeListener myLineListener;

    // private ObservableList<SingleTurtle> allTurtles;

    public TurtleList (int id) {
        myId = id;

        // initialize maps
        allTurtlesMap = new HashMap<Integer, SingleTurtle>();
        allTurtlesMap.put(1, new SingleTurtle(1));
        activeTurtlesMap = allTurtlesMap;
        myCurrentId = 1;
        //activeIds = new ArrayList<Integer>();
        //activeIds.add(1);
    }

    @Override
    public void beDrawn (Drawer drawer) {
        allTurtlesMap.values().stream().filter(SingleTurtle::isVisible)
                .forEach(turtle -> turtle.beDrawn(drawer));
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
        activeTurtlesMap.values().forEach(turtle -> {
            myCurrentId = turtle.getId();
            turtle.translate(distance);
        });
    }

    @Override
    public double rotate (double angle) {
        activeTurtlesMap.values().forEach(turtle -> {
            myCurrentId = turtle.getId();
            turtle.rotate(angle);
        });
        return angle;
    }

    @Override
    public double towards (Point2D target) {
        // TODO Auto-generated method stub
        return 0;
    }

    // TODO: fix return value, might have to use sublist
    @Override
    public double goHome () {
        double result = 0;
        activeTurtlesMap.values().forEach(turtle -> {
            myCurrentId = turtle.getId();
            turtle.goHome();
        });
        return result;
    }

    @Override
    public double show () {
        activeTurtlesMap.values().forEach(turtle -> {
            myCurrentId = turtle.getId();
            turtle.show();
        });
        return 1;
    }

    @Override
    public double hide () {
        activeTurtlesMap.values().forEach(turtle -> {
            myCurrentId = turtle.getId();
            turtle.hide();
        });
        return 0;
    }

    @Override
    public double setPenUp () {
        activeTurtlesMap.values().forEach(turtle -> {
            myCurrentId = turtle.getId();
            turtle.setPenUp();
        });
        return 1;
    }

    @Override
    public double setPenDown () {
        activeTurtlesMap.values().forEach(turtle -> {
            myCurrentId = turtle.getId();
            turtle.setPenDown();
        });
        return 0;
    }
    

    @Override
    public double setHeading (double heading) {
        activeTurtlesMap.values().forEach(turtle -> {
            myCurrentId = turtle.getId();
            turtle.setHeading(heading);
        });
        return heading;
    }

    @Override // TODO: fix return value
    public double clearScreen () {
        activeTurtlesMap.values().forEach(turtle -> {
            myCurrentId = turtle.getId();
            turtle.clearScreen();
        });
        return 0;
    }
    
    public Point2D getPosition () {
        return activeTurtlesMap.get(myCurrentId).getPosition();
    }
    
    public double getHeading () {
        return activeTurtlesMap.get(myCurrentId).getHeading();
    }

    public int getCurrentId () {
        return myCurrentId;
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

