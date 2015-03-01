package model.node.mathOperation;

import model.node.ChildBuilder;
import model.node.EvalNode;

public abstract class OneArgMathOperation extends EvalNode {

    protected double getArg () {
        return getEvalChild("arg").evaluate();
    }
    
    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("arg", EvalNode.class)};
                                   
    }
}
