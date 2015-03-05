package view;

import java.util.Map;

import model.writable.Writable;

public interface Historian {
    
//    public void record (History history);
//    
//    public void record(String key, String value);
    
    public void record(Map<String, Writable> history);
    
    // could also be
	// public void recordFeed(String feed);
	// public void recordUserDefined(String key, String value);
    
}
