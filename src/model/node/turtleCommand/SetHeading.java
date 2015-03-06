package model.node.turtleCommand;

import model.node.OneArgOperation;
import model.turtle.Turtle;

public class SetHeading extends OneArgOperation {

    private Turtle myTurtle;
      
      public SetHeading (Turtle t) {
          myTurtle = t;
      }
      
      @Override
      public double evaluate () {
          return myTurtle.setHeading(getArg()); 
      }
  }
