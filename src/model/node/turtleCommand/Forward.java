package model.node.turtleCommand;

import model.Parser;
import model.Turtle;
import model.node.EvalNode;

public class Forward extends TurtleCommand{
    
    public Forward(Turtle t){
        super(t);
        addChildNames(new String[] {"distance"});
    }
    
    public double evaluate () {
        return getTurtle().translate(getDistance().evaluate()); 
    }

    protected EvalNode getDistance () {
        return getChild("distance");
    }
}
