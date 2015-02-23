package model.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;

public final class Database {

    private static Database instance;
    private static Map<String, String[]> varsMap = new HashMap<String, String[]>();
    private static Map<String, String[][]> cmdsMap = new HashMap<String, String[][]>();
    private static List<Turtle> turtleList = new ArrayList<Turtle>();
    
    private static Map<String, String> viewerVarsMap = FXCollections.observableMap(new HashMap<String, String>());
    private static Map<String, String> viewerCmdsMap = FXCollections.observableMap(new HashMap<String, String>());
    

    private Database () {
    }

    public static synchronized Database getInstance () {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public void addVariable (String name, String[] value) {
        varsMap.put(name, value);
        viewerVarsMap.put(name, join(value, " "));
    }

    public void addCommand (String name, String[] args, String[] value) {
        String[][] mapValue = { args, value };
        cmdsMap.put(name, mapValue);
        // add to cmdsMap;
    }

    public String[] getVariable (String name) {
        return varsMap.get(name);
    }

    public String[][] getCommand (String name) {
        return cmdsMap.get(name);
    }
    
    public Turtle getTurtle(int id){
        return TurtleList.get(id);
    }
    
    public Map<String, String> getViewerVarsMap(){
      return viewerVarsMap;
    }
    
    public Map<String, String> getViewerCmdsMap(){
        return viewerCmdsMap;
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
