package model.database;

import model.writable.Writable;

/**
 * Interface that allows commands to write to a database
 * @author Nathan Prabhu
 *
 */

public interface Writer {
    
    public void write(Writable writable);

    public void setDefiningSignal(boolean bool);
    
    public boolean getDefiningSignal ();
}

