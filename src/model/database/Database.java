package model.database;


import model.writable.Writable;

public interface Database extends Writer {

    public Writable getWritable (String name);

}
