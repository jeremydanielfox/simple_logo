package model.database;

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
}
