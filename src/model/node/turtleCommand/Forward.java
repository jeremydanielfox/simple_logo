package model.node.turtleCommand;

import model.Turtle;
import model.node.ChildBuilder;
import model.node.EvalNode;


public class Forward extends TurtleCommand {

    public Forward (Turtle t) {
        super(t);
    }

    public double evaluate () {
        return getTurtle().translate(getDistance().evaluate());
    }

    protected EvalNode getDistance () {
        return getEvalChild("distance");
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("distance", EvalNode.class) };
    }
}
