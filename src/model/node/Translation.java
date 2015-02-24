package model.node;

import model.database.Database;

public class Translation extends TreeNode {

    private boolean positive;

    public Translation (boolean positive) {
        this.positive = positive;

    }

    public double evaluate () {
        double distance = isPositive() ? getDistance().evaluate() : -getDistance().evaluate();
        return Database.getInstance().getTurtle(0).translateTurtle(distance); 
        // to be refactored later.. 
    }

    private TreeNode getDistance () {
        return getChild("distance");
    }

    private boolean isPositive () {
        return positive;
    }

}
