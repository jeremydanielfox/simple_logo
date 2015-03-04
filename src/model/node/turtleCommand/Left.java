package model.node.turtleCommand;

import model.node.OneArgOperation;
import model.turtle.SingleTurtle;


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