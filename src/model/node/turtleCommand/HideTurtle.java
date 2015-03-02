package model.node.turtleCommand;

import model.Turtle;
import model.node.ZeroArgOperation;

public class HideTurtle extends ZeroArgOperation {

    private Turtle myTurtle;
    
    public HideTurtle (Turtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.setInvisible();
    }
}