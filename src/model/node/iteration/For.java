package model.node.iteration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import model.node.ChildBuilder;
import model.node.TreeNode;


public class For extends DoTimes {
	
	private ChildBuilder childBuilder;
	
    public For () {
        super();
        childBuilder = new ChildBuilder(new LinkedList<String>(), new LinkedList<Class<? extends TreeNode>>(Arrays.asList(Forward.class, Constant.class)));
        addChildNames(new String[] { "increment" });
    }
    

    @Override
    public Queue<String> getTokenTracker () {
        Queue<String> queue =
                new LinkedList<String>(Arrays.asList("ListStart", "Variable", "Other", "Other", "Other",
                                                     "ListEnd"));
        return queue;
    }

    @Override
    protected double getIncrement () {
        return getIncrementChild().evaluate();
    }

    private TreeNode getIncrementChild () {
        return getChild("increment");
    }
}
