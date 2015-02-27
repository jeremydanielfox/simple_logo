package model.node.turtleCommand;

import model.Turtle;
import model.node.EvalNode;

public class Right extends TurtleCommand {
    
    public Right(Turtle t){
        super(t);
        addChildNames(new String[] {"angle"});
    }
    
    public double evaluate () {
        return getTurtle().rotate(getAngle().evaluate()); 
    }

    protected EvalNode getAngle () {
        return getChild("angle");
    }
}
