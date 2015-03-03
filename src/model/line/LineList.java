package model.line;

import java.util.Collections;
import java.util.List;
import view.Drawer;
import javafx.geometry.Point2D;

// would belong to a turtle
public class LineList implements Line {
    private static int ourId = 0;

    private int myId;
    private List<SingleLine> myList;

    public LineList (int id, List<SingleLine> list) {
        myId = id;
        myList = list;
    }

    @Override
    public void beDrawn (Drawer drawer) {
        myList.forEach(line -> line.beDrawn(drawer));
    }

    public void add (SingleLine line) {
        myList.add(line);
    }
    
    public void clear () {
        myList.clear();
    }


    // not sure if useful at all...
    public Point2D getStart () {
        return myList.get(0).getStart();
    }

    public Point2D getFinish () {
        return myList.get(myList.size() - 1).getFinish();
    }

    public int getId () {
        return myId;
    }

    // unmodifable list... make immutable?
    // observable will be ObservableList<LineList>?
    public List<SingleLine> getList () {
        return Collections.unmodifiableList(myList);
    }
    
    public String toString () {
        return myList.toString();
    }

}
