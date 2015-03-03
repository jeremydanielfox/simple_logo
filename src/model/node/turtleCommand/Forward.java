package model.node.turtleCommand;

import turtle.SingleTurtle;
import model.node.OneArgOperation;


public class Forward extends OneArgOperation {

  private SingleTurtle myTurtle;
    
    public Forward (SingleTurtle t) {
        myTurtle = t;
    }
    
    @Override
    public double evaluate () {
        double result = getArg();
        myTurtle.translate(result); 
        return result;
    }
}
