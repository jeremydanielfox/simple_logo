package model.node.writer;

import java.util.List;
import model.database.Database;
import model.database.Writer;
import model.node.ChildBuilder;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.Parameters;
import model.node.basic.Variable;
import model.writable.VariableWritable;
import model.writable.Writable;

// TODO: put in appropriate package
public class MakeVariable extends EvalNode {
    
    private Writer myWriter;

    public MakeVariable (Writer writer) {
        myWriter = writer;
    }
    
    @Override
    public double evaluate () {
        update();
        return getEvalChild("var").evaluate();
    }

    private void update () {
        Database.getInstance().putVariable(getEvalChild("var").toString(),
                                           getEvalChild("expr"));
    }
    
    private void update2 () {
        myWriter.write(new VariableWritable(getEvalChild("var").toString(),
                                            getEvalChild("expr")));
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("var", Variable.class),
                                   new ChildBuilder("expr", EvalNode.class) };
    }
}

