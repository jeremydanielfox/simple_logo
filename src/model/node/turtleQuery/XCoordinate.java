package model.node.turtleQuery;

import model.database.Database;
import model.node.ZeroArgOperation;
import model.turtle.Turtle;
import model.writable.ConsoleWritable;


public class XCoordinate extends ZeroArgOperation {

    private Database myDatabase;
    private Turtle myTurtle;

    public XCoordinate (Turtle turtle, Database database) {
        myTurtle = turtle;
        myDatabase = database;
    }

    @Override
    public double evaluate () {
        double result = myTurtle.getPosition().getX();
        myDatabase.write(new ConsoleWritable(String.valueOf(result)));
        return result;
    }

}
