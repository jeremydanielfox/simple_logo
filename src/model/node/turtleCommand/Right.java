package model.node.turtleCommand;

import model.Turtle;
import model.node.OneArgOperation;


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