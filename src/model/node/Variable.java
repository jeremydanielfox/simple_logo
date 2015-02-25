package model.node;

import model.database.Database;


public class Variable extends TreeNode {

    private String myName;

    public Variable (String name) {
        myName = name;
        setChildNames(new String[] { "value" });
    }

    @Override
    public double evaluate () {
        double value = getValue().evaluate();
        // should update since this will store final result of expression, not actual expression
        Database.getInstance().putVariable(myName, new String[] { String.valueOf(value) });
        return value;
    }

    public void update (double increment) {
        // since evaluate already called above, only a single token (a double) should be stored... not an expression
        double currentVal = Double.parseDouble(Database.getInstance().getVariable(myName)[0]);
        Database.getInstance().putVariable(myName,
                                           new String[] { String.valueOf(currentVal + increment) });
    }

    private TreeNode getValue () {
        return getChild("value");
    }

}
