package model.node;

public abstract class ZeroArgOperation extends EvalNode {
    
    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[0];
    }

}
