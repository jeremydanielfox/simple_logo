package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.database.Database.CommandWrapper;
import model.database.IHistory;
import model.database.WorkspaceHistory;
import model.database.Writer;
import model.line.LineListCollection;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.basic.Variable;
import model.turtle.SingleTurtle;
import model.turtle.TurtleList;

public class Workspace implements IHistory {
    private static int ourId = 0;
    
    private int myId;
    private LineListCollection myLineLists; 
    private TurtleList myTurtles;
    // private DisplayProperties myDisplay;
    private WorkspaceHistory myHistory;
    
    //private Map<String,ObservableList<Drawable>> myDrawableMap;
    
    private static Map<String, EvalNode> varsMap = new HashMap<String, EvalNode>();
    private static Map<String, CommandWrapper> cmdsMap = new HashMap<String, CommandWrapper>();
    private static boolean definingSignal = false;

    private static ObservableList<String> feedHistory = FXCollections
            .observableArrayList(new ArrayList<String>());
    private static ObservableMap<String, String> varsHistory = FXCollections
            .observableMap(new HashMap<String, String>());
    private static ObservableMap<String, String> cmdsHistory = FXCollections
            .observableMap(new HashMap<String, String>());
    
    
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

    @Override
    public void putVariable (String name, EvalNode node) {
        varsMap.put(name, node);
        varsHistory.put(name, node.toString());
    }

    @Override
    public void putCommand (String name, List<Variable> params, CommandList list) {
        cmdsMap.put(name, new CommandWrapper(params, list));
        // TODO: put in cmdsHistory
    }

    @Override
    public void setDefiningSignal (boolean bool) {
        definingSignal = bool;
    }

    @Override
    public boolean getDefiningSignal () {
        return definingSignal;
    }

    @Override
    public EvalNode getVariable (String name) {
        return varsMap.get(name);
    }

    @Override
    public CommandWrapper getCommand (String name) {
        return cmdsMap.get(name);
    }

    @Override
    public ObservableList<String> getFeedHistory () {
        FXCollections.unmodifiableObservableList(feedHistory);
        return feedHistory;
    }

    @Override
    public ObservableMap<String, String> getVarsHistory () {
        return varsHistory;
    }

    @Override
    public ObservableMap<String, String> getCmdsHistory () {
        return cmdsHistory;
    }
    
//    public static class CommandWrapper {
//        private List<Variable> params;
//        private CommandList list;
//
//        public CommandWrapper (List<Variable> params, CommandList list) {
//            this.params = params;
//            this.list = list;
//        }
//
//        public List<Variable> getParameters () {
//            return params;
//        }
//
//        public CommandList getCommandList () {
//            return list;
//        }
//    }
//    

}
