package model.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import model.writable.CommandWritable;
import model.writable.VariableWritable;
import model.writable.Writable;
import view.Historian;

public class WorkspaceHistory implements Recordable, Writer, Database {

    //private List<String> feedHistory;
    private Map<Class<? extends Writable>, Map <String, Writable>> historiesMap;
    private Map<String, Writable> feedMap;
    private Map<String, Writable> varsMap;
    private Map<String, Writable> cmdsMap;
    private List<Map<String, Writable>> histories;

    
    public WorkspaceHistory () {     
        initializeMaps();
    }
    
    private void initializeMaps () {
        feedMap = new HashMap<String, Writable>(); //?
        varsMap = new HashMap<String, Writable>();
        cmdsMap = new HashMap<String, Writable>();
        historiesMap = new HashMap<Class<? extends Writable>, Map<String, Writable>>();
        historiesMap.put(CommandWritable.class, cmdsMap);
        historiesMap.put(VariableWritable.class, varsMap);    
        histories = new ArrayList<Map<String, Writable>>(Arrays.asList(feedMap, varsMap, cmdsMap));
    }

    @Override
    public void beRecorded (Historian historian) {
        histories.forEach(history -> historian.record(history));
    }

    @Override
    public void write (Writable writable) {
        // front-end will just call writable.getValue;
        Map<String, Writable> map = Optional.of(historiesMap.get(writable.getClass())).orElse(feedMap);
        map.put(writable.getName(), writable);
    }
    
    @Override
    public Writable getWritable (String name){
        if (name.startsWith(":")) {
            return varsMap.get(name);
        }
        else{
            return cmdsMap.get(name);
        }
    }
}
