package model.node.turtleCommand;

import model.node.OneArgOperation;
import model.turtle.Turtle;


public class Right extends OneArgOperation {

  private Turtle myTurtle;
    
    public Right (Turtle t) {
        myTurtle = t;
    }
    
    @Override
    public double evaluate () {
        return myTurtle.rotate(getArg()); 
    }
}