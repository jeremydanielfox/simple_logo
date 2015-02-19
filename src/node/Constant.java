package node;

public class Constant extends Node {

    private double value;
    
    public Constant (double value) {
        this.value = value;
    }

    public double evaluate () {
        return value;
    }
}
