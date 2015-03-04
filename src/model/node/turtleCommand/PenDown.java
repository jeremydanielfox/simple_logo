package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.SingleTurtle;

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