package model.node.turtleCommand;

import model.node.OneArgOperation;
import model.turtle.SingleTurtle;
import model.turtle.Turtle;


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