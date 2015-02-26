package model.node;

import model.Turtle;

public class Translation extends TreeNode {

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

    private TreeNode getDistance () {
        return getChild("distance");
    }

    private boolean isPositive () {
        return positive;
    }

}
