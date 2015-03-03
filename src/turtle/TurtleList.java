package turtle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Clearable;
import model.Drawable;
import javafx.geometry.Point2D;
import view.Drawer;

// one per workspace
public class TurtleList implements Turtle, Drawable, Clearable {
    private static int ourId;
    
    private int myId;
    private List<SingleTurtle> allTurtles;
    private List<SingleTurtle> activeTurtles;
    
    public TurtleList(int id, List<SingleTurtle> list){
        myId = id;
        allTurtles = list;
    }

    @Override
    public void beDrawn (Drawer drawer) {
        allTurtles.forEach(turtle -> turtle.beDrawn(drawer));
    }
    
    public void beCleared (Drawer drawer) {
        clearScreen();
    }
    
    public void add (SingleTurtle... turtles) {
        allTurtles.addAll(Arrays.asList(turtles));
    }
    
    
    // TODO: use matchers to filter turtle ids with given list of ids
//    public void remove (int... ids){
//        myList.stream().filter(this:: filterID);
//    }
//    
//    private boolean filterId (int[] ids) {
//        
//    }

    @Override
    public void translate (double distance) {
        activeTurtles.forEach(turtle -> turtle.translate(distance));
    }

    @Override
    public double rotate (double angle) {
        activeTurtles.forEach(turtle -> turtle.rotate(angle));
        return angle;
    }

    @Override
    public double towards (Point2D target) {
        // TODO Auto-generated method stub
        return 0;
    }

    //TODO: fix return value
    @Override
    public double goHome () {
        activeTurtles.forEach(turtle -> turtle.goHome());
        return 0;
    }

    @Override
    public double clearScreen () {
        activeTurtles.forEach(turtle -> turtle.clearScreen());
        return 0;
    }

}
