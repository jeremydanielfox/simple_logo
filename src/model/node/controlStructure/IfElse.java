package model.node.controlStructure;

import model.node.ChildBuilder;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;

public class IfElse extends If {

 // to be overridden in ifelse
    protected double elseCondition () {
        return getEvalChild("falseCommands").evaluate();
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("expr", EvalNode.class),
                                    new ChildBuilder("listStart", ListStart.class),
                                    new ChildBuilder("trueCommands", CommandList.class),
                                    new ChildBuilder("listEnd", ListEnd.class),
                                    new ChildBuilder("listStart", ListStart.class),
                                    new ChildBuilder("falseCommands", CommandList.class),
                                    new ChildBuilder("listEnd", ListEnd.class)};
       
    }
}
