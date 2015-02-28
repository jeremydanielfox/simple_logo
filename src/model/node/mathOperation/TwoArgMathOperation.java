package model.node.mathOperation;

import model.node.ChildBuilder;
import model.node.EvalNode;

public abstract class TwoArgMathOperation extends EvalNode{

    
    protected double getFirstArg () {
        return getEvalChild("arg1").evaluate();
    }
    
    protected double getSecondArg () {
        return getEvalChild("arg2").evaluate();
    }
    
    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("arg1", EvalNode.class),
                                    new ChildBuilder("arg2", EvalNode.class)};
    }

}