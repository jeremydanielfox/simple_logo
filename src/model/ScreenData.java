package model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import line.SingleLine;


public class ScreenData {
    private ObservableList<SingleLine> myLines;
    private ObservableList<TurtleData> myTurtleData;

    public ScreenData (ObservableList<SingleLine> ld, ObservableList<TurtleData> td) {
        myLines = ld;
        myTurtleData = td;
    }

    public void initialize (List<Turtle> turtles) {
        myTurtleData.addAll(turtles.stream().map(this::makeTurtleData)
                .collect(Collectors.toList()));
    }
    
    // add checkIfClear method to add lines, listener that checks if a turtle is cleared, if it is, needs to 
    // clear line data
    public void update (List<Turtle> turtles) {
    	checkIfClear(turtles);
        addLines(turtles);
        setTurtleData(turtles);
    }
    
    private void checkIfClear(List<Turtle> turtles) {
    	for (Turtle current: turtles) {
    		if (current.getLineDatas().isEmpty())
    			myLines.clear();
    	}
    }
 
    private void addLines (List<Turtle> turtles) {
        for (Turtle t : turtles) { // could possibly use lambda
            myLines.addAll(t.getLineDatas());
        }
    }

    private void setTurtleData (List<Turtle> turtles) {
        myTurtleData.addAll(turtles.stream().map(this::makeTurtleData)
                            .collect(Collectors.toList()));
        //TODO: modify current TurtleData instead of creating new ones each time
    }

    public Collection<SingleLine> getLines () {
        return myLines;
    }

    public Collection<TurtleData> getTurtleData () {
        return myTurtleData;
    }

    private TurtleData makeTurtleData (Turtle t) {
        return new TurtleData(t.getPosition().getX(), t.getPosition().getY(), t.getHeading(),
                              t.getId(), t.isVisible());
    }
}
