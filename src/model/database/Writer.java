package model.database;

import model.writable.Writable;

// needs work
public interface Writer {
    
    public void write(Writable writable);

    public void setDefiningSignal(boolean bool);
    
    public boolean getDefiningSignal ();
}
