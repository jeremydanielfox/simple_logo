package model;

import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;


public class ScreenData {
    private Collection<LineData> myLines;
    //private Collection<Turtle> myTurtles;
    private Turtle myTurtle;

    public ScreenData (Turtle turtle) {
        myLines = FXCollections.observableArrayList();
        //myTurtles = FXCollections.observableArrayList();
        myTurtle = turtle;
    }
    
    public void addLines(List<LineData> data){
        myLines.addAll(data);
    }
    
//    public void addTurtles(List<Turtle> turtles){
//        myTurtles.addAll(turtles);
//    }
    
    public Collection<LineData> getLines() {
        return myLines;
    }
    
    public Turtle getTurtle(){
        return myTurtle;
    }

}
