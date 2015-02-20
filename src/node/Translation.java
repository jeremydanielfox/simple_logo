package node;

public class Translation extends Node {

    private boolean positive;

    public Translation (Node child, boolean positive) {
        getChildren().add(child);
        this.positive = positive;

    }

    public double evaluate () {
        double distance = isPositive() ? getChild().evaluate() : -getChild().evaluate();
        // update TurtleData
        return distance;
    }

    private Node getChild () {
        return getChildren().get(0);
    }

    private boolean isPositive () {
        return positive;
    }

}