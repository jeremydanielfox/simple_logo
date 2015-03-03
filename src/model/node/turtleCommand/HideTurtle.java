package model.node.turtleCommand;

import turtle.SingleTurtle;
import model.node.ZeroArgOperation;

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