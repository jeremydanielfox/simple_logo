package model.node.turtleCommand;

import turtle.SingleTurtle;
import model.node.OneArgOperation;


public class Left extends OneArgOperation {

  private SingleTurtle myTurtle;
    
    public Left (SingleTurtle t) {
        myTurtle = t;
    }
    
    @Override
    public double evaluate () {
        return myTurtle.rotate(-getArg()); 
    }
}