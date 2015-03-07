package model.node.writer;

import model.database.Writer;
import model.node.ChildBuilder;
import model.node.EvalNode;
import model.node.database.Variable;
import model.writable.VariableWritable;

/**
 * Defines the "Make" command. It has access to the Writer of the WorkspaceHistory.
 * @author Nathan Prabhu
 *
 */
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
        VariableWritable var = new VariableWritable(getEvalChild("var").toString(),
                                                    getEvalChild("expr"));
        var.setDefiningSignal(true);
        myWriter.write(new VariableWritable(getEvalChild("var").toString(),
                                            getEvalChild("expr")));
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("var", Variable.class),
                                   new ChildBuilder("expr", EvalNode.class) };
    }
}

