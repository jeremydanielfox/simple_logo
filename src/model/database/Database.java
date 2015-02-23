package model.database;

import java.util.HashMap;
import java.util.Map;

public final class Database {

    private static Database instance;
    private Map<String, String[]> varsMap;
    private Map<String, String[][]> cmdsMap;

    private Database () {
    }

    public static synchronized Database getInstance () {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public void addVariable (String name, String[] value) {
        varsMap.put(name, value);
    }

    public void addCommand (String name, String[] args, String[] value) {
        String[][] mapValue = { args, value };
        cmdsMap.put(name, mapValue);
    }

    public String[] getVariable (String name) {
        return varsMap.get(name);
    }

    public String[][] getCommand (String name) {
        return cmdsMap.get(name);
    }
    
    public Map<String, String> getViewerVarsMap(){
        Map<String, String> result = new HashMap<String,String>();
        for (String key: varsMap.keySet()){
            result.put(key, join(varsMap.get(key), " "));
        }
        return result;
    }
    
//    public Map<String, String> getViewerCmdsMap(){
//        Map<String, String> result = new HashMap<String,String>();
//        for (String key: cmdsMap.keySet()){
//            result.put(key, join(cmdsMap.get(key), " "));
//        }
//        return result;
//    }
    
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
