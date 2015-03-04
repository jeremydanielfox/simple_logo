package model.turtle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Clearable;
import model.Drawable;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import view.Clearer;
import view.Drawer;

// one per workspace
public class TurtleList implements Turtle, Clearable {
    private static int ourId;
    
    private int myId;
    private ObservableList<SingleTurtle> allTurtles;
    private List<SingleTurtle> activeTurtles;
    
    public TurtleList(int id){
        myId = id;
        allTurtles = FXCollections.observableArrayList();
        activeTurtles = allTurtles; // to be changed
    }

    @Override
    public void beDrawn (Drawer drawer) {
        allTurtles.forEach(turtle -> turtle.beDrawn(drawer));
    }
    
    public void beCleared (Clearer clearer) {
        clearScreen();
        clearer.clearTurtles();
    }
    
    public void add (SingleTurtle... turtles) {
        allTurtles.addAll(Arrays.asList(turtles));
    }
    
    public SingleTurtle get (int i){
        return allTurtles.get(i);
    }
    
    public int size () {
        return allTurtles.size();
    }
    
    
    public List<SingleTurtle> getAllTurtles(){
        return allTurtles;
    }

    // TODO: use matchers to filter turtle ids with given list of ids
//    public void remove (int... ids){
//        myList.stream().filter(this:: filterID);
//    }
//    
//    private boolean filterId (int[] ids) {
//        
//    }

    @Override
    public void translate (double distance) {
        activeTurtles.forEach(turtle -> turtle.translate(distance));
    }

    @Override
    public double rotate (double angle) {
        activeTurtles.forEach(turtle -> turtle.rotate(angle));
        return angle;
    }

    @Override
    public double towards (Point2D target) {
        // TODO Auto-generated method stub
        return 0;
    }

    //TODO: fix return value
    @Override
    public double goHome () {
        activeTurtles.forEach(turtle -> turtle.goHome());
        return 0;
    }

    @Override
    public double clearScreen () {
        activeTurtles.forEach(turtle -> turtle.clearScreen());
        return 0;
    }

    @Override
    public void addListener (InvalidationListener listener) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeListener (InvalidationListener listener) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addListener (ChangeListener<? super Drawable> listener) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeListener (ChangeListener<? super Drawable> listener) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Drawable getValue () {
        return this;
    }
	

}
