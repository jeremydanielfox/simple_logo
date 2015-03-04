package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.SingleTurtle;

public class ClearScreen extends ZeroArgOperation {
    
    private SingleTurtle myTurtle;

    public ClearScreen (SingleTurtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.clearScreen();
    }
}

