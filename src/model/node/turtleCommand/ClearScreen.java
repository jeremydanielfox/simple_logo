package model.node.turtleCommand;

import turtle.SingleTurtle;
import model.node.ZeroArgOperation;

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

