package model.node;

/**
 * Helper class that defaults a ChildBuilder to one argument
 * @author Nathan Prabhu
 *
 */
public abstract class OneArgOperation extends EvalNode {

    protected double getArg () {
        return getEvalChild("arg").evaluate();
    }
    
    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("arg", EvalNode.class)};
                                   
    }
}
