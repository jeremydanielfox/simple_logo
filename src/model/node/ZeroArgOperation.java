package model.node;

/**
 * Helper class that defaults a ChildBuilder to zero arguments
 * @author Nathan Prabhu
 *
 */
public abstract class ZeroArgOperation extends EvalNode {
    
    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[0];
    }

}
