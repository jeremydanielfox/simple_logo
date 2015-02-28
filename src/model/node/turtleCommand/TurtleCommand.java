package model.node.turtleCommand;

import model.Turtle;
import model.node.EvalNode;

public abstract class TurtleCommand extends EvalNode {

    private Turtle turtle;

    public TurtleCommand(Turtle t){
        turtle = t;
    }
    
    protected Turtle getTurtle () {
        return turtle;
    }
}
