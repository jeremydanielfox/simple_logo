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

    public void update (List<Turtle> turtles) {
        addLines(turtles);
        setTurtleData(turtles);
        System.out.println("be");
    }

    private void addLines (List<Turtle> turtles) {
        for (Turtle t : turtles) { // could possibly use lambda
            myLines.addAll(t.getLineDatas());
        }
    }

    private void setTurtleData (List<Turtle> turtles) {
        myTurtleData.addAll(turtles.stream().map(this::makeTurtleData)
                        .collect(Collectors.toList()));
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

}
