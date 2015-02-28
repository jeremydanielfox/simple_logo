package model.node.basic;

import model.database.Database;
import model.node.ChildBuilder;
import model.node.EvalNode;


public class Variable extends EvalNode {

    private String myName;

    public Variable (String name) {
        myName = name;
    }

    @Override
    public double evaluate () {
        double value = getValue().evaluate();
        // should update since this will store final result of expression, not actual expression
        update(value);
        return value;
    }

    public void update (double value) {
        Database.getInstance().putVariable(myName, new String[] { String.valueOf(value) });
    }

    private EvalNode getValue () {
        return getEvalChild("value");
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("value", EvalNode.class) };
    }

}
