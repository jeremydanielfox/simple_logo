package model.node.basic;

import java.util.Optional;
import model.database.Database;
import model.node.EvalNode;
import model.node.ZeroArgOperation;


public class Variable extends ZeroArgOperation {

    protected String myName;

    public Variable (String name) {
        myName = name;
    }

    @Override
    public double evaluate () {
        Optional<String> opt = Optional.of(myName);
        EvalNode node = opt.map(Database.getInstance()::getVariable).orElseGet(() -> new Constant("0"));
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
