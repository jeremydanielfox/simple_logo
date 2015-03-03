package model.node.turtleCommand;

import turtle.SingleTurtle;
import model.node.OneArgOperation;


public class Right extends OneArgOperation {

  private SingleTurtle myTurtle;
    
    public Right (SingleTurtle t) {
        myTurtle = t;
    }
    
    @Override
    public double evaluate () {
        return myTurtle.rotate(getArg()); 
    }
}