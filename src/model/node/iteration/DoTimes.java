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
    public Queue<String> getTokenTracker () {
        Queue<String> queue =
                new LinkedList<String>(Arrays.asList("ListStart", "Variable", "Other", "ListEnd"));
        return queue;
    }

    @Override
    protected double getVar () {
        return getVarChild().evaluate();
    }

    @Override
    protected Variable getVarChild () {
        return (Variable) getChild("var");
    }

    @Override
    protected void updateVar (double value) {
        getVarChild().update(value);
    }
}
