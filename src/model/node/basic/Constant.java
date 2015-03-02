package model.node.basic;

import model.node.ZeroArgOperation;


public class Constant extends ZeroArgOperation {

    private double value;
    
    public Constant (String value) {
        this.value = Double.parseDouble(value);
    }

    public double evaluate () {
        return value;
    }

    @Override
    public String toString () {
        return Double.toString(value) + " ";
    }
}
