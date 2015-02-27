package model.node.iteration;

import model.node.TreeNode;
import model.node.Variable;

public class Repeat extends TreeNode {

    // no arguments since default values handle all 3 iteration types
    public Repeat () {
        addChildNames(new String[] { "max", "commands"});
    }

    public double evaluate () {
        double lastEvaluation = 0;
        for (double i = getVar(); i < getMax(); i += getIncrement()) {
            lastEvaluation = getCommandsChild().evaluate();
            updateVar(i);
        }
        return lastEvaluation;
    }

    protected double getVar () {
        return 0;
    }

    protected Variable getVarChild () {
        return null;
    }

    protected double getMax () {
        return getMaxChild().evaluate(); // will be present for every iteration type
    }

    protected double getIncrement () {
        return 1;
    }

    // to be overridden in DoTimes and For
    protected void updateVar (double value) {
    }

    protected TreeNode getMaxChild () {
        return getChild("max");
    }

    protected TreeNode getCommandsChild () {
        // TODO: could hold multiple Nodes (e.g repeat 10 [fd 10 rt 90] )
        return getChild("commands");
    }
}
