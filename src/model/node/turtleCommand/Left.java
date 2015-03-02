package model.node.turtleCommand;

import model.Turtle;
import model.node.OneArgOperation;


public class Left extends OneArgOperation {

  private Turtle myTurtle;
    
    public Left (Turtle t) {
        myTurtle = t;
    }
    
    @Override
    public double evaluate () {
        return myTurtle.rotate(-getArg()); 
    }
}