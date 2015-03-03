package line;

import java.util.Collections;
import java.util.List;
import view.Drawer;


// would belong to a workspace
public class LineListCollection implements Line {
    private static int ourId = 0;

    private int myId;
    private List<LineList> myLineLists;

    public LineListCollection (int id, List<LineList> linelists) {
        myId = id;
        myLineLists = linelists;
    }

    @Override
    public void beDrawn (Drawer drawer) {
        myLineLists.forEach(linelist -> linelist.beDrawn(drawer));
    }
    
    public void beCleared (Drawer drawer) {
        myLineLists.forEach(linelist -> linelist.clear());
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
}
