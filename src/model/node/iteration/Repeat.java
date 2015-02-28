package model.node.iteration;

import model.node.ChildBuilder;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.Variable;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;

public class Repeat extends EvalNode {

    // no arguments since default values handle all 3 iteration types
    public Repeat () {
        super();
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

    protected EvalNode getMaxChild () {
        return (EvalNode) getEvalChild("max");
    }

    protected EvalNode getCommandsChild () {
        // TODO: could hold multiple Nodes (e.g repeat 10 [fd 10 rt 90] )
        return (EvalNode) getEvalChild("commands");
    }
    
    @Override
    public String toString(){
        return "Repeat";
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("max", EvalNode.class),
                                    new ChildBuilder("listStart", ListStart.class),
                                    new ChildBuilder("commands", CommandList.class),
                                    new ChildBuilder("listEnd", ListEnd.class)};
    }
}
