package node;

public class Iteration extends Node {

    // no arguments since default values handle all 3 iteration types
    public Iteration () {
    }

    public double evaluate () {
        double temp = 0;
        for (double i = getVar(); i < getMax(); i += getIncrement()) {
            temp = getCommandsChild().evaluate();
        }
        // remove local variable from database
        return temp;
    }

    private double getVar () {
        if (getVarChild() != null) { return getVarChild().evaluate();
        // add local variable to database
        }
        return 0;
    }

    private double getMax () {
        return getMaxChild().evaluate(); // will be present for every iteration type
    }

    private double getIncrement () {
        if (getIncrementChild() != null) { return getIncrementChild().evaluate(); }
        return 1;
    }

    private Node getVarChild () {
        return getChildren().get("var");
    }

    private Node getMaxChild () {
        return getChildren().get("max");
    }

    private Node getIncrementChild () {
        return getChildren().get("increment");
    }

    private Node getCommandsChild () {
        return getChildren().get("commands");
    }
}
