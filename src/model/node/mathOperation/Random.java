package model.node.mathOperation;

import model.node.OneArgOperation;

public class Random extends OneArgOperation {

    private static final java.util.Random random = new java.util.Random();

    @Override
    public double evaluate () {
        return nextDouble(getArg());
    }
    
    // implementation of Math's nextDouble with a bound
    private double nextDouble (double bound) {
        return bound * random.nextDouble();
    }

}
