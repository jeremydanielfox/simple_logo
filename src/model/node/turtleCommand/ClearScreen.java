package model.node.turtleCommand;

import model.Turtle;
import model.node.ZeroArgOperation;

public class ClearScreen extends ZeroArgOperation {
    
    private Turtle myTurtle;

    public ClearScreen (Turtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.clearScreen();
    }
}

