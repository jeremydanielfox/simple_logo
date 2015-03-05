package model.line;

import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import model.Clearable;
import model.turtle.SingleTurtle;
import model.turtle.TurtleList;
import view.Clearer;
import view.Drawer;


// would belong to a workspace
public class LineListCollection implements Line, Clearable {

    private int myId;
    private ObservableList<LineList> myLineLists;

    public LineListCollection () {
        myId = 0;
    }

    public LineListCollection (int id, TurtleList turtles) {
        myId = id;
        myLineLists = FXCollections.observableArrayList();
        for (SingleTurtle turtle : turtles.getAllTurtles()) {
            myLineLists.add(turtle.getLines());
        }
    }

    @Override
    public void beDrawn (Drawer drawer) {
        myLineLists.forEach(linelist -> linelist.beDrawn(drawer));
    }

    public List<LineList> getLineLists () {
        return Collections.unmodifiableList(myLineLists);
    }

    public int getId () {
        return myId;
    }

    public String toString () {
        return myLineLists.toString();
    }

    @Override
    public void beCleared (Clearer clearer) {
        myLineLists.forEach(linelist -> linelist.beCleared(clearer));

    }

    public void addListener (ListChangeListener<? super Line> listener) {
        myLineLists.forEach(lineList -> lineList.addListener(listener));
    }
}
