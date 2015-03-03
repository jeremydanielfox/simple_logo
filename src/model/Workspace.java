package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import turtle.SingleTurtle;
import turtle.TurtleList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import line.LineListCollection;
import model.database.Database.CommandWrapper;
import model.database.History;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.basic.Variable;

public class Workspace implements History {
    private static int ourId = 0;
    
    private int myId;
    private LineListCollection myLineLists; 
    private TurtleList myTurtles;
    // TODO:
    // private DisplayProperties myDisplay;
    
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
    
    
    public Workspace(TurtleList turtles, LineListCollection linelists){
        myId = ourId++;
        myTurtles = turtles;
        myLineLists = linelists;
        
        // initializes one turtle by default
        addTurtle();
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
