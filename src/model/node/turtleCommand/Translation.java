package model.node.turtleCommand;

import model.Turtle;
import model.node.EvalNode;

public class Translation extends EvalNode {

    private boolean positive;
    private Turtle turtle;

    public Translation (boolean positive, Turtle t) {
        addChildNames(new String[] {"distance"});
        this.positive = positive;
        turtle = t;

    }

    public double evaluate () {
        double distance = isPositive() ? getDistance().evaluate() : -getDistance().evaluate();
        return turtle.translate(distance); 
    }

    private EvalNode getDistance () {
        return getChild("distance");
    }

    private boolean isPositive () {
        return positive;
    }

}
