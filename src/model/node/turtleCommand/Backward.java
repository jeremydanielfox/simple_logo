package model.node.turtleCommand;

import model.Turtle;

public class Backward extends Forward {
    

    public Backward (Turtle t) {
        super(t);
    }
    
    @Override
    public double evaluate () {
        return getTurtle().translate(-getDistance().evaluate()); 
    }
    
}
