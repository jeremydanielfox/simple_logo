package model.node.mathOperation;

import model.node.OneArgOperation;

public class Sine extends OneArgOperation {

    @Override
    public double evaluate () {
        return Math.sin(Math.toRadians(getArg()));
    }

}
