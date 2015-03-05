package model;

import model.database.Database;
import model.database.Recordable;
import model.database.WorkspaceHistory;
import model.database.Writer;
import model.line.LineListCollection;
import model.turtle.SingleTurtle;
import model.turtle.TurtleList;

public class Workspace {
    private static int ourId = 0;
    
    private int myId;
    private LineListCollection myLineLists; 
    private TurtleList myTurtles;
    // private DisplayProperties myDisplay;
    private WorkspaceHistory myHistory;

    
    public Workspace(){
        myId = ourId++;
        myTurtles = new TurtleList(myId);
        
        // initializes one by default
        addTurtle();
        myLineLists = new LineListCollection(myId, myTurtles);
        myHistory = new WorkspaceHistory();
        
    }
    
    // adds turtles one at time
    public void addTurtle(){
        myTurtles.add(new SingleTurtle());
    }

    public int getId () {
        return myId;
    }


    public LineListCollection getLines () {
        return myLineLists;
    }


    public TurtleList getTurtles () {
        return myTurtles;
    }
    
    public Writer getWriter() {
        return (Writer) myHistory;
    }
    
    public Recordable getRecordable() {
        return (Recordable) myHistory;
    }
    
    public Database getDatabase () {
        return (Database) myHistory;
    }

}
