package model.node.database;

import java.util.Optional;
import model.database.Database;
import model.database.OldDatabase;
import model.node.EvalNode;
import model.node.ZeroArgOperation;
import model.node.basic.Constant;
import model.writable.VariableWritable;


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
        VariableWritable writable = ((VariableWritable) myDatabase.getWritable(myName));
        if (writable != null){
            return writable.getExpression().evaluate();
        }
        else{
            return new Constant("0").evaluate();
        }
 
//      EvalNode node =  Optional.of(myName).map(OldDatabase.getInstance()::getVariable)
//                        .orElseGet( () -> new Constant("0"));
    }

    // for DoTimes and For
    public void update (double value) {
        myDatabase.write(new VariableWritable(myName, new Constant(String.valueOf(value))));
        //OldDatabase.getInstance().putVariable(myName, new Constant(String.valueOf(value)));
    }

    @Override
    public String toString () {
        return myName;
    }
}
