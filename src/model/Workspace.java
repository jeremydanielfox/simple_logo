package model;

import model.database.Database;
import model.database.Recordable;
import model.database.WorkspaceHistory;
import model.database.Writer;
import model.turtle.TurtleList;


/**
 * Represents a single workspace from the model's perspective. Turtles and workspace history can be
 * retrieved from it.
 * 
 * @author Nathan PRabhu
 *
 */
public class Workspace {
    private static int ourId = 0;

    private int myId;
    private TurtleList myTurtles;
    // private DisplayProperties myDisplay;
    private WorkspaceHistory myHistory;

    public Workspace () {
        myId = ourId++;
        myTurtles = new TurtleList();
        myHistory = new WorkspaceHistory();
    }

    public int getId () {
        return myId;
    }

    public TurtleList getTurtles () {
        return myTurtles;
    }

    public Writer getWriter () {
        return (Writer) myHistory;
    }

    public Recordable getRecordable () {
        return (Recordable) myHistory;
    }

    public Database getDatabase () {
        return (Database) myHistory;
    }

    public WorkspaceHistory getWorkspaceHistory () {
        return myHistory;
    }

}
