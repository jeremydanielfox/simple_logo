package model.node;

import java.util.Queue;

public class Repeat extends TreeNode {

    // no arguments since default values handle all 3 iteration types
    public Repeat () {
    }

    public double evaluate () {
        double temp = 0;
        for (double i = getVar(); i < getMax(); i += 1) {
            temp = getCommandsChild().evaluate();
            // update local variable by increment
        }
        // remove local variable from database
        return temp;
    }

    protected double getVar () {
        if (getVarChild() != null) { return getVarChild().evaluate();
        // add local variable to database
        }
        return 0;
    }

    protected double getMax () {
        return getMaxChild().evaluate(); // will be present for every iteration type
    }

    protected TreeNode getVarChild () {
        // just a string will do
        return getChild("var");
    }

    protected TreeNode getMaxChild () {
        return getChild("max");
    }

    protected TreeNode getCommandsChild () {
        // TODO: could hold multiple Nodes (e.g repeat 10 [fd 10 rt 90] )
        return getChild("commands");
    }
}
