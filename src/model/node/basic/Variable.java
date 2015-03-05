package model.node.basic;

import java.util.Optional;
import model.database.Database;
import model.database.Writer;
import model.node.EvalNode;
import model.node.ZeroArgOperation;
import model.writable.Writable;


public class Variable extends ZeroArgOperation {

    protected String myName;
    //private Writer myWriter;

    public Variable  (String name) {
        myName = name;
        //myWriter = writer;
    }

    @Override
    public double evaluate () {
        EvalNode node =
                Optional.of(myName).map(Database.getInstance()::getVariable)
                        .orElseGet( () -> new Constant("0"));
        return node.evaluate();
    }

    // for DoTimes and For
    public void update (double value) {
        Database.getInstance().putVariable(myName, new Constant(String.valueOf(value)));
    }

    @Override
    public String toString () {
        return myName;
    }
}
