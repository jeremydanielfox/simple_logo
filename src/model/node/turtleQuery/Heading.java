package model.node.turtleQuery;

import model.database.Database;
import model.node.ZeroArgOperation;
import model.turtle.Turtle;
import model.writable.ConsoleWritable;


public class Heading extends ZeroArgOperation {

    private Database myDatabase;
    private Turtle myTurtle;

    public Heading (Turtle turtle, Database database) {
        myTurtle = turtle;
        myDatabase = database;
    }

    @Override
    public double evaluate () {
        double result = myTurtle.getHeading();
        myDatabase.write(new ConsoleWritable(String.valueOf(result)));
        return result;
    }

}
