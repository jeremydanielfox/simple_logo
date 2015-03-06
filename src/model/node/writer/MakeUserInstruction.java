package model.node.writer;

import model.database.Writer;
import model.node.ChildBuilder;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.Parameters;
import model.node.database.Command;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;
import model.writable.CommandWritable;


public class MakeUserInstruction extends EvalNode {

    private Writer myWriter;

    public MakeUserInstruction (Writer writer) {
        super();
        writer.setDefiningSignal(true);
        myWriter = writer;
    }

    // TODO: determine how command could be unsuccessfully added...
    @Override
    public double evaluate () {
        update();
        return 1;
    }

    private void update () {
        myWriter.write(new CommandWritable(getEvalChild("name").toString(),
                                           ((Parameters) getChild("params")),
                                           (CommandList) getEvalChild("commands")));
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
