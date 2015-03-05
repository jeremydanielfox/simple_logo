package model.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.TreeNode;
import model.node.basic.Variable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;


public final class Database {

    private static Database instance;
    private static Map<String, EvalNode> varsMap = new HashMap<String, EvalNode>();
    private static Map<String, CommandWrapper> cmdsMap = new HashMap<String, CommandWrapper>();
    private static boolean definingSignal = false;

    private static ObservableList<String> feedHistory = FXCollections
            .observableArrayList(new ArrayList<String>());
    private static ObservableMap<String, String> varsHistory = FXCollections
            .observableMap(new HashMap<String, String>());
    private static ObservableMap<String, String> cmdsHistory = FXCollections
            .observableMap(new HashMap<String, String>());

    private Database () {
    }

    public static synchronized Database getInstance () {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public void addFeed (String feed) {
        feedHistory.add(feed);
    }

    public void putVariable (String name, EvalNode node) {
        varsMap.put(name, node);
        varsHistory.put(name, node.toString());
    }

    public void putCommand (String name, List<Variable> params, CommandList list) {
        cmdsMap.put(name, new CommandWrapper(params, list));
        cmdsHistory.put(name, cmdsMap.get(name).toString());
    }

    // used to indicate from To that next token will be a command
    public void setDefiningSignal (boolean bool) {
        definingSignal = bool;
    }

    public boolean getDefiningSignal () {
        return definingSignal;
    }

    public EvalNode getVariable (String name) {
        return varsMap.get(name);
    }

    public CommandWrapper getCommand (String name) {
        return cmdsMap.get(name);
    }

    // should somehow restrict modification with all Histories..
    public ObservableList<String> getFeedHistory () {
        FXCollections.unmodifiableObservableList(feedHistory);
        return feedHistory;
    }

    public ObservableMap<String, String> getVarsHistory () {
        return varsHistory;
    }

    public ObservableMap<String, String> getCmdsHistory () {
        return cmdsHistory;
    }
    
    public void printVarsHistory(){  //for testing
    	 for (String s : getVarsHistory().keySet()){
    		 System.out.println(s + " " + getVarsHistory().get(s));
		 }
    }
    
    public void printCmdsHistory(){  //for testing
    	System.out.println("CmdsHistory:");
    	for (String s : getCmdsHistory().keySet()){
    		System.out.println(s + " " + getCmdsHistory().get(s));
		}
    	System.out.println("EndCmdsHistory");
    }

    public static class CommandWrapper {
        private List<Variable> params;
        private CommandList list;

        public CommandWrapper (List<Variable> params, CommandList list) {
            this.params = params;
            this.list = list;
        }

        public List<Variable> getParameters () {
            return params;
        }

        public CommandList getCommandList () {
            return list;
        }
        
        public String toString(){
        	return listToString(params) + "[ " + list.toString() + "]";
        }
        
        private String listToString(List<Variable> list){
        	String string = "";
        	for (Variable s : list){
        		string += s.toString();
        	}
        	return "[ " + string + "] ";
        }
    }
}
