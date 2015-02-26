package model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;


public class ScreenData {
    private ObservableList<LineData> myLines;
    private ObservableList<TurtleData> myTurtleData;

    public ScreenData (ObservableList<LineData> ld, ObservableList<TurtleData> td) {
        myLines = ld;
        myTurtleData = td;
    }

    public void initialize (List<Turtle> turtles) {
        myTurtleData.addAll(turtles.stream().map(this::makeTurtleData)
                .collect(Collectors.toList()));
    }

    public void update (List<Turtle> turtles) {
        addLines(turtles);
        setTurtleData(turtles);
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

    public Collection<LineData> getLines () {
        return myLines;
    }

    public Collection<TurtleData> getTurtleData () {
        return myTurtleData;
    }

    private TurtleData makeTurtleData (Turtle t) {
        return new TurtleData(t.getPosition().getX(), t.getPosition().getY(), t.getHeading(),
                              t.getId(), t.isVisible());
    }

    // should refactor with lambda
    private void updateTurtleData (Turtle turtle) {
        TurtleData td = myTurtleData.get(turtle.getId()); // should hopefully be synced
        td.setLocation(turtle.getPosition());
        td.setHeading(turtle.getHeading());
        td.setVisible(turtle.isVisible());
    }

}
