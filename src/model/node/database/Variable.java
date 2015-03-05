package model.node.database;

import java.util.Optional;
import model.database.Database;
import model.database.OldDatabase;
import model.database.Writer;
import model.node.EvalNode;
import model.node.ZeroArgOperation;
import model.node.basic.Constant;
import model.writable.Writable;


public class Variable extends ZeroArgOperation {

    protected String myName;
    private Database myDatabase;

    public Variable  (String name, Database database) {
        myName = name;
        myDatabase = database;
        //myWriter = writer;
    }

    @Override
    public double evaluate () {
        EvalNode node =
                Optional.of(myName).map(OldDatabase.getInstance()::getVariable)
                        .orElseGet( () -> new Constant("0"));
        return node.evaluate();
    }

    // for DoTimes and For
    public void update (double value) {
        OldDatabase.getInstance().putVariable(myName, new Constant(String.valueOf(value)));
    }

    @Override
    public String toString () {
        return myName;
    }
}
