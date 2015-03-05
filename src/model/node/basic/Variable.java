package model.node.basic;

import model.database.Database;
import model.node.ZeroArgOperation;


public class Variable extends ZeroArgOperation {

    protected String myName;

    public Variable (String name) {
        myName = name;
    }

    @Override
    public double evaluate () {
        return Database.getInstance().getVariable(myName).evaluate();
    }

    // for DoTimes and For
    public void update (double value) {
        Database.getInstance().putVariable(myName, new Constant(String.valueOf(value)));
    }
    
    @Override
    public String toString () {
        return myName + " ";
    }
}
