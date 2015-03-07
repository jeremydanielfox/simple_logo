package model.database;

import javafx.collections.MapChangeListener;
import view.Historian;

/**
 *  Property of something that can be written to a historian
 * @author Nathan Prabhu
 *
 */
public interface Recordable {
    
    public void beRecorded(Historian recorder);
    
    public void addListener(MapChangeListener change);
    
}
