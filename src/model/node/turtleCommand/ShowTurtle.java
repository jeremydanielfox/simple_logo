package model.node.turtleCommand;

import model.node.ZeroArgOperation;
import model.turtle.Turtle;

public class ShowTurtle extends ZeroArgOperation {
    
    private Turtle myTurtle;

    public ShowTurtle (Turtle t) {
        myTurtle = t;
    }

    @Override
    public double evaluate () {
        return myTurtle.setVisible();
    }
}
