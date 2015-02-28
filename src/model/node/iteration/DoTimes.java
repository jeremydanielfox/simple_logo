package model.node.iteration;

import model.node.ChildBuilder;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.basic.Variable;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;


public class DoTimes extends Repeat {

    public DoTimes (String type) {
        super(type);
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

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("listStart", ListStart.class),
                                    new ChildBuilder("var", Variable.class),
                                    new ChildBuilder("max", EvalNode.class),
                                    new ChildBuilder("listEnd", ListEnd.class),
                                    new ChildBuilder("listStart", ListStart.class),
                                    new ChildBuilder("commands", CommandList.class),
                                    new ChildBuilder("listEnd", ListEnd.class)};
    }
}
