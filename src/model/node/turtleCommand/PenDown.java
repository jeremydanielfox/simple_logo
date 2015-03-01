package model.node.turtleCommand;

import model.Turtle;

public class PenDown extends PenUp {

    public PenDown (Turtle t) {
        super(t);
    }
    
    @Override
    public double evaluate () {
        return getTurtle().setPenDown();
    }

}
