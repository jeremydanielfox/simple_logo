package model.node.mathOperation;

import model.node.TwoArgOperation;

public class Product extends TwoArgOperation {

    @Override
    protected double evaluateHelper (double arg1, double arg2) {
        return arg1 + arg2;
    }

}
