package model.node.turtleCommand;

import model.Turtle;
import model.node.OneArgOperation;


public class Forward extends OneArgOperation {

  private Turtle myTurtle;
    
    public Forward (Turtle t) {
        myTurtle = t;
    }
    
    @Override
    public double evaluate () {
        return myTurtle.translate(getArg()); 
    }
}
