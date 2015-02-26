package model.node.turtleCommand;

import model.Turtle;

public class Left extends Right {

    public Left (Turtle t) {
        super(t);
    }
    
    @Override
    public double evaluate () {
        return getTurtle().rotate(-getAngle().evaluate()); 
    }

}
