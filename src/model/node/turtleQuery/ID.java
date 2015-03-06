package model.node.turtleQuery;

import model.database.Database;
import model.node.ZeroArgOperation;
import model.turtle.Turtle;
import model.turtle.TurtleList;
import model.writable.ConsoleWritable;

public class ID extends ZeroArgOperation {

    private Database myDatabase;
    private TurtleList myTurtle;
    
    public ID (Turtle t, Database database) {
        myTurtle = (TurtleList) t;
        myDatabase = database;
    }

    @Override
    public double evaluate () {
        int result = myTurtle.getCurrentId();
        myDatabase.write(new ConsoleWritable(String.valueOf(result)));
        return result;
    }
}
