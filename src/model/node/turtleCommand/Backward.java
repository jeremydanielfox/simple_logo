package model.node.turtleCommand;

import model.Turtle;
import model.node.OneArgOperation;

public class Backward extends OneArgOperation {
    
    private Turtle myTurtle;
    
    public Backward (Turtle t) {
        myTurtle = t;
    }
    
    @Override
    public double evaluate () {
        return myTurtle.translate(-getArg()); 
    }
}