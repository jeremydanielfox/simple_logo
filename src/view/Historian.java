package view;

import java.util.Map;

public interface Historian {
    
    public void record(String key, String value);
    
    public void record(Map<String, String> history);
    
    // could also be
    public void recordFeed(String feed);
    public void recordUserDefined(String key, String value);

    
}
