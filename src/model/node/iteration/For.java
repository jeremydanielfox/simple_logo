package model.node.iteration;

import model.node.EvalNode;


public class For extends DoTimes {

    public For () {
        super();
        addChildNames(new String[] { "increment" });
    }
    
    @Override
    protected double getIncrement () {
        return getIncrementChild().evaluate();
    }

    private EvalNode getIncrementChild () {
        return (EvalNode) getChild("increment");
    }
}
