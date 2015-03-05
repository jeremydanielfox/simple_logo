package model.node.database;

import java.util.ArrayList;
import java.util.List;
import Exceptions.UnrecognizedTokenException;
import model.database.Database;
import model.database.OldDatabase;
import model.node.ChildBuilder;
import model.node.EvalNode;
import model.writable.Writable;


public class Command extends EvalNode {

    private String name;
    private Database database;
    private List<Variable> params;

    public Command (String name, Database database) {
        super(true);
        // two cases:
        // 1) being defined (e.g. to square[:dist])
        // 2) all further times (e.g. square 50)
        this.name = name;
        this.database = database;
        params = new ArrayList<Variable>();
        if (beingDefined()){
            OldDatabase.getInstance().setDefiningSignal(false);
            super.setChildBuilders();
        }
        else{
            verify();
            params = OldDatabase.getInstance().getCommand(name).getParameters();
            super.setChildBuilders();       
        }
    }

    @Override
    public double evaluate () {
        for (int i = 0; i < params.size(); i++) {
            params.get(i).update(getEvalChild(String.format("var%d", i)).evaluate());
        }
        return OldDatabase.getInstance().getCommand(name).getCommandList().evaluate();
    }

    protected boolean beingDefined () {
        return (OldDatabase.getInstance().getDefiningSignal());
    }
    
    @Override
    public ChildBuilder[] addChildBuilders () {
        List<ChildBuilder> result = new ArrayList<ChildBuilder>();
        for (int i = 0; i < params.size(); i++) {
            result.add(new ChildBuilder(String.format("var%d", i), EvalNode.class));
        }
        return result.toArray(new ChildBuilder[result.size()]);
    }

    private void verify () {
        if (OldDatabase.getInstance().getCommand(name).equals(null)) { 
            throw new UnrecognizedTokenException(name); 
        }
    }

    @Override
    public String toString () {
        return name;
    }
}
