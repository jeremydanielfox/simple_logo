package model;

import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;

public class Workspace {
    private Map<String,ObservableList<Drawable>> myDrawableMap;
    private List<Turtle> myTurtles;
    
    public Workspace(Map<String,ObservableList<Drawable>> map){
        myDrawableMap = map;
    }
    
    // for each turtle, add appropriate turtledata to list
    // for each turtle, add appropriate linedata to list
    
    // TODO: for each history, have an interface that allows them to be passed around
    
    // addFeed
    // putVar & putCmd
    // getVar & getCmd

}
