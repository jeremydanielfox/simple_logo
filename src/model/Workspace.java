package model;

import java.util.List;
import java.util.Map;
import turtle.SingleTurtle;
import turtle.TurtleList;
import javafx.collections.ObservableList;
import line.LineListCollection;

public class Workspace {
    private static int ourId = 0;
    
    private int myId;
    private LineListCollection myLineLists; 
    private TurtleList myTurtles;
    //private Map<String,ObservableList<Drawable>> myDrawableMap;
    
    
    public Workspace(TurtleList turtles, LineListCollection linelists){
        myId = ourId++;
        myTurtles = turtles;
        myLineLists = linelists;
    }

    public int getId () {
        return myId;
    }


    public LineListCollection getLines () {
        return myLineLists;
    }


    public TurtleList getTurtles () {
        return myTurtles;
    }
    
    
    
    // for each turtle, add appropriate turtledata to list
    // for each turtle, add appropriate linedata to list
    
    // TODO: for each history, have an interface that allows them to be passed around
    
    // addFeed
    // putVar & putCmd
    // getVar & getCmd

}
