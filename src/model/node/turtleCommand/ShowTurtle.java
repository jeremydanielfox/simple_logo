package model.node.turtleCommand;

import model.Turtle;
import model.node.ZeroArgOperation;

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
