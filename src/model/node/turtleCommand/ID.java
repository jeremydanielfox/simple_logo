package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.Turtle;
import model.turtle.TurtleList;

public class ID extends ZeroArgOperation {

 private TurtleList myTurtle;
    
    public ID (Turtle t) {
        myTurtle = (TurtleList) t;
    }

    @Override
    public double evaluate () {
        return myTurtle.getCurrentId();
    }
}
