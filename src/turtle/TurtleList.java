package turtle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import view.Drawer;

// one per workspace
public class TurtleList implements Turtle {
    private static int ourId;
    
    private int myId;
    private List<SingleTurtle> myList;
    
    public TurtleList(int id, List<SingleTurtle> list){
        myId = id;
        myList = list;
    }

    @Override
    public void beDrawn (Drawer drawer) {
        myList.forEach(turtle -> turtle.beDrawn(drawer));
    }
    
    public void add (SingleTurtle... turtles) {
        myList.addAll(Arrays.asList(turtles));
    }
    
    
    // TODO: use matchers to filter turtle ids with given list of ids
//    public void remove (int... ids){
//        myList.stream().filter(this:: filterID);
//    }
//    
//    private boolean filterId (int[] ids) {
//        
//    }

}
