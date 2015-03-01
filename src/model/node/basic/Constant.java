package model.node.basic;

import model.node.ChildBuilder;
import model.node.EvalNode;


public class Constant extends EvalNode {

    private double value;
    
    public Constant (String value) {
        this.value = Double.parseDouble(value);
    }

    public double evaluate () {
        return value;
    }

    @Override
    public String toString () {
        return Double.toString(value);
    }
    
    @Override
    public boolean hasChildren(){  //for testing
    	return false;
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[0];
    }
}
