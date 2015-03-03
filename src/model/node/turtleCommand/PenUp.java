package model.node.turtleCommand;

import turtle.SingleTurtle;
import model.node.ZeroArgOperation;

public class PenUp extends ZeroArgOperation {

    private SingleTurtle myTurtle;
    
    public PenUp (SingleTurtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.setPenUp();
    }
}
