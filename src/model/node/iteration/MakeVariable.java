package model.node.iteration;

import model.database.Database;
import model.node.ChildBuilder;
import model.node.EvalNode;
import model.node.basic.Variable;

// TODO: put in appropriate package
public class MakeVariable extends EvalNode {

    public MakeVariable () {
        super();
    }

    @Override
    public double evaluate () {
        update();
        return getEvalChild("var").evaluate();
    }

    public void update () {
        Database.getInstance().putVariable(getEvalChild("var").toString(),
                                           getEvalChild("expr"));
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("var", Variable.class),
                                   new ChildBuilder("expr", EvalNode.class) };
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }

}
