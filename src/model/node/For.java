package model.node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class For extends Repeat {

	@Override
	public double evaluate () {
        double temp = 0;
        for (double i = getVar(); i < getMax(); i += getIncrement()) {
            temp = getCommandsChild().evaluate();
            // update local variable by increment
        }
        // remove local variable from database
        return temp;
    }
	
	@Override
	public Queue<String> getTokenTracker() {
		Queue<String> queue = new LinkedList<String>(Arrays.asList("ListStart", "Variable", "Other", "Other", "ListEnd"));
		return queue;
	}
	
	private TreeNode getIncrementChild () {
        return getChild("increment");
    }
	
	private double getIncrement () {
        if (getIncrementChild() != null) { return getIncrementChild().evaluate(); }
        return 1;
    }
}
