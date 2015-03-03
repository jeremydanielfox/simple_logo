package model.node.turtleCommand;

import turtle.SingleTurtle;
import model.node.ZeroArgOperation;

public class PenDown extends ZeroArgOperation {

    private SingleTurtle myTurtle;
    
    public PenDown (SingleTurtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.setPenDown();
    }
}