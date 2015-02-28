package model.node;


public class Constant extends EvalNode {

    private double value;
    
    public Constant (double value) {
        this.value = value;
    }

    public double evaluate () {
        return value;
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[0];
    }
}
