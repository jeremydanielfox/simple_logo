package model.node.turtleCommand;

import model.Turtle;
import model.node.TreeNode;

public abstract class TurtleCommand extends TreeNode {

    private Turtle turtle;

    public TurtleCommand(Turtle t){
        turtle = t;
    }
    
    protected Turtle getTurtle () {
        return turtle;
    }
}
