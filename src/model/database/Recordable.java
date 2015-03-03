package model.database;

import view.Historian;

public interface Recordable {
    
    public void beRecorded(Historian recorder);

}
