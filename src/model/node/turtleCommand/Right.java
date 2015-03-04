package model.node.turtleCommand;

import model.node.OneArgOperation;
import model.turtle.SingleTurtle;


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