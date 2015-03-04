package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.SingleTurtle;

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
