package model.node;

public class Translation extends Node {

    private boolean positive;

    public Translation (boolean positive) {
        this.positive = positive;

    }

    public double evaluate () {
        double distance = isPositive() ? getDistance().evaluate() : -getDistance().evaluate();
        // update TurtleData
        return distance;
    }

    private Node getDistance () {
        return getChild("distance");
    }

    private boolean isPositive () {
        return positive;
    }

}
