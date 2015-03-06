package model.node.database;

import java.util.ArrayList;
import java.util.List;
import model.database.Database;
import model.node.ChildBuilder;
import model.node.EvalNode;
import model.writable.CommandWritable;
import Exceptions.UnrecognizedTokenException;


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
            database.setDefiningSignal(false);
            super.setChildBuilders();
        }
        else{
            verify();
            params = ((CommandWritable) database.getWritable(name)).getParameters();
            super.setChildBuilders();       
        }
    }

    @Override
    public double evaluate () {
        for (int i = 0; i < params.size(); i++) {
            params.get(i).update(getEvalChild(String.format("var%d", i)).evaluate());
        }
        return ((CommandWritable) database.getWritable(name)).getCommands().evaluate();
    }

    protected boolean beingDefined () {
        return database.getDefiningSignal();
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
        if (database.getWritable(name) == null){
            throw new UnrecognizedTokenException(name); 
        }
    }

    @Override
    public String toString () {
        return name;
    }
}
