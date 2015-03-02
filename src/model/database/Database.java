package model.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import model.node.EvalNode;
import model.node.TreeNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public final class Database {

	private static Database instance;
	private static Map<String, EvalNode> varsMap = new HashMap<String, EvalNode>();
	private static Map<String, String[][]> cmdsMap = new HashMap<String, String[][]>();

	private static ObservableList<String> feedHistory = FXCollections
			.observableArrayList(new ArrayList<String>());
	private static ObservableMap<String, String> varsHistory = FXCollections
			.observableMap(new HashMap<String, String>());
	private static ObservableMap<String, String> cmdsHistory = FXCollections
			.observableMap(new HashMap<String, String>());

<<<<<<< HEAD
	// private Database () {
	// }
=======
    public static synchronized Database getInstance () {
        if (instance == null)
            instance = new Database();
        return instance;
    }
    
    public void addFeed (String feed){
        feedHistory.add(feed);
    }
    
    public void putVariable (String name, EvalNode node) {
        varsMap.put(name, node);
        varsHistory.put(name, reverseParse(node));
        //System.out.println(varsHistory.get(name)); //for testing
        // TODO: traverse tree to get string representation of nodes
        //varsHistory.put(name, node.toString());
    }
>>>>>>> 34d90279a753e3e955922e5291838984d6c0e474

	public Database(ObservableList<String> feed,
			ObservableMap<String, String> vars,
			ObservableMap<String, String> cmds) {
		feedHistory = feed;
		varsHistory = vars;
		cmdsHistory = cmds;
	}

//	public static synchronized Database getInstance() {
//		if (instance == null)
//			instance = new Database();
//		return instance;
//	}

<<<<<<< HEAD
	public void addFeed(String feed) {
		feedHistory.add(feed);
	}

	public void putVariable(String name, EvalNode node) {
		varsMap.put(name, node);
		// TODO: traverse tree to get string representation of nodes
		// varsHistory.put(name, node.toString());
	}

	public void putCommand(String name, String[] args, String[] value) {
		String[][] mapValue = { args, value };
		cmdsMap.put(name, mapValue);
		// add to cmdsHistory;
	}

	public EvalNode getVariable(String name) {
		return varsMap.get(name);
	}

	public String[][] getCommand(String name) {
		return cmdsMap.get(name);
	}

	// should somehow restrict modification with all Histories..
	public ObservableList<String> getFeedHistory() {
		FXCollections.unmodifiableObservableList(feedHistory);
		return feedHistory;
	}

	public ObservableMap<String, String> getVarsHistory() {
		return varsHistory;
	}

	public ObservableMap<String, String> getCmdsHistory() {
		return cmdsHistory;
	}
=======
    public String[][] getCommand (String name) {
        return cmdsMap.get(name);
    }
    
    // should somehow restrict modification with all Histories..
    public ObservableList<String> getFeedHistory(){
        FXCollections.unmodifiableObservableList(feedHistory);
        return feedHistory;
    }
    
    public ObservableMap<String, String> getVarsHistory(){
      return varsHistory;
    }
    
    public ObservableMap<String, String> getCmdsHistory(){
        return cmdsHistory;
    }
    
//    public void updateVariables(){   //not necessary (I think)
//    	for (String s : varsMap.keySet()){
//    		if (!varsHistory.keySet().contains(s)){
//    			varsHistory.put(s, reverseParse(varsMap.get(s)));
//    		}
//    	}
//    }
    
    private String reverseParse(EvalNode node){
    	String nodeString = "";
//    	if (node.hasChildren()){
    		return node.toString() + " " + helper(node, nodeString);
//    	}
//    	else {
//    		return node.toString();
//    	}
    	
    }
    
    private String helper(TreeNode node, String s){
    	//println(node);
    	//println(node.getChildren().values());
    	for (TreeNode tn : node.getChildren().values()){
    		//println(tn);
    		s += tn.toString() + " ";
			if (tn.hasChildren()){
				s +=  helper(tn, s);
			}
		} 
    	return s; 
    }
    
    public void printVarsHistory(){  //for testing
    	 for (String s : getVarsHistory().keySet()){
    		 System.out.println(s + " " + getVarsHistory().get(s));
		 }
    }
    
    public void println(Object line) { //for testing purposes
        System.out.println(line);
    }
   
>>>>>>> 34d90279a753e3e955922e5291838984d6c0e474
}
