package model.node.controlStructure;

import model.database.Database;
import model.node.ChildBuilder;
import model.node.EvalNode;
import model.node.basic.Variable;

// TODO: put in appropriate package
public class MakeVariable extends EvalNode {
    
    @Override
    public double evaluate () {
        update();
        return getEvalChild("var").evaluate();
    }

    private void update () {
        Database.getInstance().putVariable(getEvalChild("var").toString(),
                                           getEvalChild("expr"));
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("var", Variable.class),
                                   new ChildBuilder("expr", EvalNode.class) };
    }
}
