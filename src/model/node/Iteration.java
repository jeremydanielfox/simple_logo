package model.node;

public class Iteration extends Node {

    // no arguments since default values handle all 3 iteration types
    public Iteration () {
    }

    public double evaluate () {
        double temp = 0;
        for (double i = getVar(); i < getMax(); i += getIncrement()) {
            temp = getCommandsChild().evaluate();
            // update local variable by increment
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
        // just a string will do
        return getChild("var");
    }

    private Node getMaxChild () {
        return getChild("max");
    }

    private Node getIncrementChild () {
        return getChild("increment");
    }

    private Node getCommandsChild () {
        return getChild("commands");
    }
}
