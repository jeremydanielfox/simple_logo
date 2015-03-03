package model.node.controlStructure;

import java.util.List;
import model.database.Database;
import model.database.Writer;
import model.node.ChildBuilder;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.Parameters;
import model.node.basic.Command;
import model.node.basic.Variable;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;
import model.writable.CommandWritable;
import model.writable.Writable;


public class MakeUserInstruction extends EvalNode {

    private Writer myWriter;

    public MakeUserInstruction (Writer writer) {
        super();
        Database.getInstance().setDefiningSignal(true);
        myWriter = writer;
    }

    // TODO: determine how command could be unsuccessfully added...
    @Override
    public double evaluate () {
        update();
        return 1;
    }

    private void update2 () {
        myWriter.write(new CommandWritable(getEvalChild("name").toString(),
                                           ((Parameters) getEvalChild("params")).getList(),
                                           (CommandList) getEvalChild("commands")));
    }

    private void update () {
        Database.getInstance().putCommand(getEvalChild("name").toString(),
                                          ((Parameters) getEvalChild("params")).getList(),
                                          (CommandList) getEvalChild("commands"));
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("name", Command.class),
                                   new ChildBuilder("listStart", ListStart.class),
                                   new ChildBuilder("params", Parameters.class),
                                   new ChildBuilder("listEnd", ListEnd.class),
                                   new ChildBuilder("listStart", ListStart.class),
                                   new ChildBuilder("commands", CommandList.class),
                                   new ChildBuilder("listEnd", ListEnd.class) };
    }

}
