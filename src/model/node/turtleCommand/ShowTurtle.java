package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.SingleTurtle;

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
