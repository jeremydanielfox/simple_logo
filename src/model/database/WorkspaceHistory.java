package model.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import model.node.basic.Constant;
import model.writable.CommandWritable;
import model.writable.VariableWritable;
import model.writable.Writable;
import view.Historian;

public class WorkspaceHistory implements Recordable, Writer {

    //private List<String> feedHistory;
    private Map<Class<? extends Writable>, Map <String, Writable>> historiesMap;
    private Map<String, Writable> feedMap;
    private Map<String, Writable> varsMap;
    private Map<String, Writable> cmdsMap;
    private List<Map<String, Writable>> histories;
    
//    private History feedHistory;
//    private History cmdHistory;
//    private History varHistory;
//    private List<History> myHistories;
    
    public WorkspaceHistory () {     
        initializeMaps();
//        feedHistory = new History("feed");
//        cmdHistory = new History("command");
//        varHistory = new History("variable");
//        myHistories = new ArrayList<History>(Arrays.asList(feedHistory,cmdHistory, varHistory));
    }
    
    private void initializeMaps () {
        feedMap = new HashMap<String, Writable>(); //?
        varsMap = new HashMap<String, Writable>();
        cmdsMap = new HashMap<String, Writable>();
        historiesMap = new HashMap<Class<? extends Writable>, Map<String, Writable>>();
        historiesMap.put(CommandWritable.class, cmdsMap);
        historiesMap.put(VariableWritable.class, varsMap);    
        histories.addAll(Arrays.asList(feedMap, varsMap, cmdsMap));
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
}
