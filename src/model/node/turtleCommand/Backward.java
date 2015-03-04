package model.node.turtleCommand;

import model.node.OneArgOperation;
import model.turtle.SingleTurtle;

public class Backward extends OneArgOperation {
    
    private SingleTurtle myTurtle;
    
    public Backward (SingleTurtle t) {
        myTurtle = t;
    }
    
    @Override
    public double evaluate () {
        double result = -getArg();
        myTurtle.translate(result); 
        return result;
    }
}