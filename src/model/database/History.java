package model.database;

import java.util.HashMap;
import java.util.Map;
import view.Historian;

public class History implements Recordable {
    
    private String myType;
    private Map<String, String> myMap;
    
    public History (String type) {
        myType = type;
        myMap = new HashMap<String, String>();
    }

    @Override
    public void beRecorded (Historian recorder) {
        //recorder.record(myMap);
    }

}
