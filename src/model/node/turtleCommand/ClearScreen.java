package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.Turtle;

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

