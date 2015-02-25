package model.node;

import model.database.Database;


public class Variable extends TreeNode {

    private String myName;

    public Variable (String name) {
        myName = name;
        addChildNames(new String[] { "value" });
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

    private TreeNode getValue () {
        return getChild("value");
    }

}
