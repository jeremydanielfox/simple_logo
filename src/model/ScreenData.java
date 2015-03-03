package model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import turtle.SingleTurtle;
import javafx.collections.ObservableList;
import line.SingleLine;


public class ScreenData {
    private ObservableList<SingleLine> myLines;
    private ObservableList<TurtleData> myTurtleData;

    public ScreenData (ObservableList<SingleLine> ld, ObservableList<TurtleData> td) {
        myLines = ld;
        myTurtleData = td;
    }

    public void initialize (List<SingleTurtle> turtles) {
        myTurtleData.addAll(turtles.stream().map(this::makeTurtleData)
                .collect(Collectors.toList()));
    }
    
    // add checkIfClear method to add lines, listener that checks if a turtle is cleared, if it is, needs to 
    // clear line data
    public void update (List<SingleTurtle> turtles) {
    	checkIfClear(turtles);
        addLines(turtles);
        setTurtleData(turtles);
    }
    
    private void checkIfClear(List<SingleTurtle> turtles) {
    	for (SingleTurtle current: turtles) {
    		if (current.getLines().isEmpty())
    			myLines.clear();
    	}
    }
 
    private void addLines (List<SingleTurtle> turtles) {
        for (SingleTurtle t : turtles) { // could possibly use lambda
            myLines.addAll(t.getLines());
        }
    }

    private void setTurtleData (List<SingleTurtle> turtles) {
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

    private TurtleData makeTurtleData (SingleTurtle t) {
        return new TurtleData(t.getPosition().getX(), t.getPosition().getY(), t.getHeading(),
                              t.getId(), t.isVisible());
    }
}
