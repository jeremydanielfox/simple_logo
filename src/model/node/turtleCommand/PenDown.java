package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.Turtle;

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