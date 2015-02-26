package model.node.turtleCommand;

import model.Turtle;
import model.node.TreeNode;

public class Forward extends TurtleCommand{
    
    public Forward(Turtle t){
        super(t);
        addChildNames(new String[] {"distance"});
    }
    
    public double evaluate () {
        return getTurtle().translate(getDistance().evaluate()); 
    }

    protected TreeNode getDistance () {
        return getChild("distance");
    }
}
