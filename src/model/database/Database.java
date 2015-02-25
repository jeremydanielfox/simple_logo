package model.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Turtle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public final class Database {

    private static Database instance;
    private static Map<String, String[]> varsMap = new HashMap<String, String[]>();
    private static Map<String, String[][]> cmdsMap = new HashMap<String, String[][]>();
    private static List<Turtle> turtleList = new ArrayList<Turtle>();
    
    private static ObservableList<String> feedHistory = FXCollections.observableArrayList(new ArrayList<String>());
    private static ObservableMap<String, String> varsHistory = FXCollections.observableMap(new HashMap<String, String>());
    private static ObservableMap<String, String> cmdsHistory = FXCollections.observableMap(new HashMap<String, String>());
    

    private Database () {
    }

    public static synchronized Database getInstance () {
        if (instance == null)
            instance = new Database();
        return instance;
    }
    
    public void addTurtle(Turtle turtle){
        turtleList.add(turtle);
    }

    public void addFeed (String feed){
        feedHistory.add(feed);
    }
    public void addVariable (String name, String[] value) {
        varsMap.put(name, value);
        varsHistory.put(name, join(value, " "));
    }

    public void addCommand (String name, String[] args, String[] value) {
        String[][] mapValue = { args, value };
        cmdsMap.put(name, mapValue);
        // add to cmdsHistory;
    }

    public String[] getVariable (String name) {
        return varsMap.get(name);
    }

    public String[][] getCommand (String name) {
        return cmdsMap.get(name);
    }
    
    public Turtle getTurtle(int id){
        return turtleList.get(id);
    }
    
    // should somehow restrict modification with all Histories..
    public ObservableList<String> getFeedHistory(){
        FXCollections.unmodifiableObservableList(feedHistory);
        return feedHistory;
    }
    
    public ObservableMap<String, String> getVarsHistory(){
      return varsHistory;
    }
    
    public Map<String, String> getCmdsHistory(){
        return cmdsHistory;
    }
    
    private String join(String[] parts, String delim){
        StringBuilder result = new StringBuilder();
        int counter=0;
        
        for (String part: parts){
            counter++;
            result.append(part);
            if (delim != null && counter < parts.length) {
                result.append(delim);
            }        
        }
        return result.toString();
    }
}
