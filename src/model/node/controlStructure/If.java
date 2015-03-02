package model.node.controlStructure;

import model.node.ChildBuilder;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;


public class If extends EvalNode {

    @Override
    public double evaluate () {
        if (!(getEvalChild("expr").evaluate() == 0)){
            return getEvalChild("trueCommands").evaluate();
        }
        else{
            return elseCondition();
        }
    }

    // to be overridden in ifelse
    protected double elseCondition () {
        return 0;
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("expr", EvalNode.class),
                                    new ChildBuilder("listStart", ListStart.class),
                                    new ChildBuilder("trueCommands", CommandList.class),
                                    new ChildBuilder("listEnd", ListEnd.class)};
       
    }
}
