package model.node.iteration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import model.node.Variable;

public class DoTimes extends Repeat {

    public DoTimes () {
        super();
        addChildNameFirst("var");
    }

    @Override
    protected double getVar () {
        return getVarChild().evaluate();
    }

    @Override
    protected Variable getVarChild () {
        return (Variable) getEvalChild("var");
    }

    @Override
    protected void updateVar (double value) {
        getVarChild().update(value);
    }
}
