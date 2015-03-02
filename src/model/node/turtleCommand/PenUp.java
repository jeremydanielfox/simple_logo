package model.node.turtleCommand;

import model.Turtle;
import model.node.ZeroArgOperation;

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
