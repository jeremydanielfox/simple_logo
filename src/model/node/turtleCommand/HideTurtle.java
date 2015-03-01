package model.node.turtleCommand;

import model.Turtle;

public class HideTurtle extends ShowTurtle {

    public HideTurtle (Turtle t) {
        super(t);
    }
    
    @Override
    public double evaluate () {
        return getTurtle().setInvisible();
    }

}
