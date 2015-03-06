package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.Turtle;

public class HideTurtle extends ZeroArgOperation {

    private Turtle myTurtle;
    
    public HideTurtle (Turtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.hide();
    }
}