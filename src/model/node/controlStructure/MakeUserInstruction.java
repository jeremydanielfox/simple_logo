package model.node.controlStructure;

import model.database.Database;
import model.node.ChildBuilder;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.Parameters;
import model.node.basic.Command;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;


public class MakeUserInstruction extends EvalNode {
    
    public MakeUserInstruction(){
        super();
        Database.getInstance().setDefiningSignal(true);
    }

    // TODO: determine how command could be unsuccessfully added...
    @Override
    public double evaluate () {
        update();
        return 1;
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

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }

}
