package model.node.turtleCommand;

import model.Turtle;
import model.node.ChildBuilder;
import model.node.EvalNode;

public class Right extends TurtleCommand {
    
    public Right(Turtle t){
        super(t);
    }
    
    public double evaluate () {
        return getTurtle().rotate(getAngle().evaluate()); 
    }

    protected EvalNode getAngle () {
        return getEvalChild("angle");
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] {new ChildBuilder("angle", EvalNode.class)};
    }
}
