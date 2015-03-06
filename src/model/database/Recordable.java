package model.database;

import javafx.collections.MapChangeListener;
import view.Historian;

public interface Recordable {
    
    public void beRecorded(Historian recorder);
    
    public void addListener(MapChangeListener change);
    
}
