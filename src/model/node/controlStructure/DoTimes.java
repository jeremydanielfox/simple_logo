package model.node.controlStructure;

import model.node.ChildBuilder;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.database.Variable;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;


public class DoTimes extends Repeat {
    
    @Override 
    protected double getStart () {
        updateVar(0);
        return 0;
    }

    @Override
    protected void updateVar (double value) {
        ((Variable) getEvalChild("var")).update(value);
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("listStart", ListStart.class),
                                    new ChildBuilder("var", Variable.class),
                                    new ChildBuilder("max", EvalNode.class),
                                    new ChildBuilder("listEnd", ListEnd.class),
                                    new ChildBuilder("listStart", ListStart.class),
                                    new ChildBuilder("commands", CommandList.class),
                                    new ChildBuilder("listEnd", ListEnd.class)};
    }
}
