package model.node.turtleCommand;

import model.node.OneArgOperation;
import model.turtle.Turtle;


public class Forward extends OneArgOperation {

  private Turtle myTurtle;
    
    public Forward (Turtle turtle) {
        myTurtle = turtle;
    }
    
    @Override
    public double evaluate () {
        double result = getArg();
        myTurtle.translate(result); 
        return result;
    }
}
