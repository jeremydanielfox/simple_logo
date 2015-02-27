package model.node.iteration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import model.node.ChildBuilder;
import model.node.EvalNode;
import model.node.turtleCommand.Forward;


public class For extends DoTimes {

    private ChildBuilder childBuilder;

    public For () {
        super();
        childBuilder =
                new ChildBuilder(new LinkedList<String>(),
                                 new LinkedList<Class<? extends EvalNode>>(Arrays
                                         .asList(Forward.class)));
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
