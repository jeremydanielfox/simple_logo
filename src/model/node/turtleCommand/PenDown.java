package model.node.turtleCommand;

import model.Turtle;
import model.node.ZeroArgOperation;

public class PenDown extends ZeroArgOperation {

    private Turtle myTurtle;
    
    public PenDown (Turtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.setPenDown();
    }
}