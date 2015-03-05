package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.Turtle;

public class PenUp extends ZeroArgOperation {

    private Turtle myTurtle;
    
    public PenUp (Turtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.setPenUp();
    }
}
