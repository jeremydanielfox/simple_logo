package model.node.turtleCommand;

import turtle.SingleTurtle;
import model.node.ZeroArgOperation;

public class ShowTurtle extends ZeroArgOperation {
    
    private SingleTurtle myTurtle;

    public ShowTurtle (SingleTurtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.setVisible();
    }
}
