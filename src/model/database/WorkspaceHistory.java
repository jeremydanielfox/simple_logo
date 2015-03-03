package model.database;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.writable.Writable;
import view.Historian;

public class WorkspaceHistory implements Recordable, Writer {

    private List<String> feedHistory;
    private List<Map <String, String>> histories;
    private Map<String, String> feedMap; 
    private Map<String, String> varsMap;
    private Map<String, String> cmdsMap;
    
    
    
    @Override
    public void beRecorded (Historian historian) {
        histories.forEach(map -> historian.record(map));
    }



    @Override
    public void write (Writable writable) {
        // TODO Auto-generated method stub
        
    }
    
//    public void beWritten (Writer writer) {
//        
//    }

    
    // historian.record(map.forEach((k,v) -> historian.record(k,v))));

}
