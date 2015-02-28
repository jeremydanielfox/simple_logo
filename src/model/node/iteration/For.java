package model.node.iteration;

import model.node.ChildBuilder;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.basic.Variable;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;


public class For extends DoTimes {

    public For (String type) {
        super(type);
    }
    
    @Override
    protected double getIncrement () {
        return getIncrementChild().evaluate();
    }

    private EvalNode getIncrementChild () {
        return (EvalNode) getEvalChild("increment");
    }
    
    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("listStart", ListStart.class),
                                    new ChildBuilder("var", Variable.class),
                                    new ChildBuilder("max", EvalNode.class),
                                    new ChildBuilder("increment", EvalNode.class),
                                    new ChildBuilder("listEnd", ListEnd.class),
                                    new ChildBuilder("listStart", ListStart.class),
                                    new ChildBuilder("commands", CommandList.class),
                                    new ChildBuilder("listEnd", ListEnd.class)};
    }
}
