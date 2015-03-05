package model.turtle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.InvalidationListener;
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
    
    private ObservableList<SingleTurtle> allTurtles;

    public TurtleList (int id) {
        myId = id;
        allTurtles = FXCollections.observableArrayList();
        //adds turtle by default;
        allTurtles.add(new SingleTurtle(1));
        
        // initialize maps
        allTurtlesMap = new HashMap<Integer, SingleTurtle>();
        allTurtlesMap.put(allTurtles.get(0).getId(), allTurtles.get(0));
        activeTurtlesMap = allTurtlesMap;
    }

    @Override
    public void beDrawn (Drawer drawer) {
        // could be a map
        allTurtles.forEach(turtle -> turtle.beDrawn(drawer));
    }

    public void beCleared (Clearer clearer) {
        clearScreen();
        clearer.clearTurtles();
    }
    
    public void setActive (List<Integer> idList) {
       activeTurtlesMap = new HashMap<Integer, SingleTurtle>();
       for (int id: idList){
           if (allTurtlesMap.containsKey(id)){
               activeTurtlesMap.put(id, allTurtlesMap.get(id));
           }
           else {
               SingleTurtle turtle = new SingleTurtle(id);
               activeTurtlesMap.put(id, turtle);
               allTurtlesMap.put(id, turtle);
           }
       }
    }

    public void add (SingleTurtle ... turtles) {
        allTurtles.addAll(Arrays.asList(turtles));
    }

    public SingleTurtle get (int i) {
        return allTurtles.get(i);
    }

    public int size () {
        return allTurtles.size();
    }

    public List<SingleTurtle> getAllTurtles () {
        return allTurtles;
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
    public double setVisible () {
        activeTurtlesMap.values().forEach(turtle -> turtle.setVisible());
        return 1;
    }

    @Override
    public double clearScreen () {
        activeTurtlesMap.values().forEach(turtle -> turtle.clearScreen());
        return 0;
    }
    
    public int getId () {
        return myId;
    }

    public void addLocationListener (InvalidationListener listener) {
       allTurtles.forEach(turtle -> turtle.addLocationListener(listener));
    }
    
    public void addHeadingListener (InvalidationListener listener) {
        allTurtles.forEach(turtle -> turtle.addHeadingListener(listener));
    }



}
