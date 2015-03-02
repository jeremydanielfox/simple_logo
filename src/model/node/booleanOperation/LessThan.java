package model.node.booleanOperation;

import model.node.TwoArgOperation;


public class LessThan extends TwoArgOperation {

    @Override
    protected double evaluateHelper (double arg1, double arg2) {
        return arg1 < arg2 ? 1 : 0;
    }

}
