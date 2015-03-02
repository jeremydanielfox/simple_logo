package model.node;


public abstract class OneArgOperation extends EvalNode {

    protected double getArg () {
        return getEvalChild("arg").evaluate();
    }
    
    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("arg", EvalNode.class)};
                                   
    }
}
