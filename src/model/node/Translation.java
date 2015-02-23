package model.node;

public class Translation extends TreeNode {

    private boolean positive;

    public Translation (boolean positive) {
        this.positive = positive;

    }

    public double evaluate () {
        double distance = isPositive() ? getDistance().evaluate() : -getDistance().evaluate();
        //myTurtle.getId(0).translate(distance);
        return distance;
    }

    private TreeNode getDistance () {
        return getChild("distance");
    }

    private boolean isPositive () {
        return positive;
    }

}
