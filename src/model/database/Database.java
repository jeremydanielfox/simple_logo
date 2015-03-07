package model.database;


import model.writable.Writable;

/**
 * Combines Writer with a reading functionality.
 * @author Nathan Prabhu
 *
 */

public interface Database extends Writer {

    public Writable getWritable (String name);

}
