package model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;


public class ScreenData {
    private Collection<LineData> myLines;
    private Collection<TurtleData> myTurtleData;

    public ScreenData () {
        myLines = FXCollections.observableArrayList();
        myTurtleData = FXCollections.observableArrayList();
    }
    
    public void update(List<Turtle> turtles) {
        addLines(turtles);
        setTurtleData(turtles);
    }

    private void addLines (List<Turtle> turtles) {
        for (Turtle t: turtles){ // could possibly use lambda
            myLines.addAll(t.getLineDatas());
        }
    }

    private void setTurtleData (List<Turtle> turtles) {
        myTurtleData = turtles.stream().map(this::makeTurtleData).collect(Collectors.toList());
    }

    public Collection<LineData> getLines () {
        return myLines;
    }

    public Collection<TurtleData> getTurtleData () {
        return myTurtleData;
    }
    
    private TurtleData makeTurtleData (Turtle t){
        return new TurtleData(t.getPosition().getX(), t.getPosition().getY(), t.getHeading(),
                              t.getId(), t.isVisible());
    }

}
