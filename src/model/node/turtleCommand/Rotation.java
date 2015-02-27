package model.node.turtleCommand;

import model.Turtle;
import model.node.EvalNode;

public class Rotation extends EvalNode {

    private boolean clockwise;
    private Turtle turtle;

    public Rotation (boolean clockwise, Turtle t) {
        addChildNames(new String[] {"angle"});
        this.clockwise = clockwise;
        turtle = t;
    }

    public double evaluate () {
        double angle = isClockwise() ? getAngle().evaluate() : -getAngle().evaluate();
        return turtle.rotate(angle); 
    }

    private EvalNode getAngle () {
        return getChild("angle");
    }

    private boolean isClockwise () {
        return clockwise;
    }

}
