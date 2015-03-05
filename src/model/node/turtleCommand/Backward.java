package model.node.turtleCommand;

import model.node.OneArgOperation;
import model.turtle.SingleTurtle;
import model.turtle.Turtle;

public class Backward extends OneArgOperation {
    
    private Turtle myTurtle;
    
    public Backward (Turtle t) {
        myTurtle = t;
    }
    
    @Override
    public double evaluate () {
        double result = -getArg();
        myTurtle.translate(result); 
        return result;
    }
}