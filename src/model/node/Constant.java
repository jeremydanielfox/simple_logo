package model.node;

public class Constant extends EvalNode {

    private double value;
    
    public Constant (double value) {
        this.value = value;
    }

    public double evaluate () {
        return value;
    }
}
