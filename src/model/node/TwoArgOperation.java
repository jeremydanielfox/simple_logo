package model.node;

/**
 * Helper class that defaults a ChildBuilder to two arguments
 * @author Nathan Prabhu
 *
 */
public abstract class TwoArgOperation extends EvalNode{
    
    @Override
    public double evaluate () {
        double arg1 = getEvalChild("arg1").evaluate();
        double arg2 = getEvalChild("arg2").evaluate();
        return evaluateHelper(arg1, arg2);  
    }
    
    protected abstract double evaluateHelper (double arg1, double arg2);

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[] { new ChildBuilder("arg1", EvalNode.class),
                                    new ChildBuilder("arg2", EvalNode.class)};
    }

}