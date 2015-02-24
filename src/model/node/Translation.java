package model.node;

import model.Turtle;
import model.database.Database;

public class Translation extends TreeNode {

    private boolean positive;
    private Turtle turtle;

    public Translation (boolean positive, Turtle t) {
        this.positive = positive;
        turtle = t;

    }

    public double evaluate () {
        double distance = isPositive() ? getDistance().evaluate() : -getDistance().evaluate();
        return turtle.translate(distance); 
        // to be refactored later.. 
    }

    private TreeNode getDistance () {
        return getChild("distance");
    }

    private boolean isPositive () {
        return positive;
    }

}
