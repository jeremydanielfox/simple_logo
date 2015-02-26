package model.node.turtleCommand;

import model.Turtle;
import model.node.TreeNode;

public class Forward extends TreeNode{

    private Turtle turtle;

    public Forward(Turtle t){
        addChildNames(new String[] {"distance"});
        turtle = t;
    }
    
    public double evaluate () {
        return turtle.translate(getDistance().evaluate()); 
    }

    private TreeNode getDistance () {
        return getChild("distance");
    }
}
