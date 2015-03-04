package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.SingleTurtle;

public class HideTurtle extends ZeroArgOperation {

    private SingleTurtle myTurtle;
    
    public HideTurtle (SingleTurtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.setInvisible();
    }
}