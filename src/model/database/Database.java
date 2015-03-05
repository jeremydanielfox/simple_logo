package model.database;

import model.writable.Writable;

public interface Database {

    public Writable getWritable (String name);
}
