package model.node.mathOperation;

public class Random extends OneArgMathOperation {

    private static final java.util.Random random = new java.util.Random();

    @Override
    public double evaluate () {
        return nextDouble(getArg());
    }
    
    // implementation of Math's nextDouble with a bound
    private double nextDouble (double bound) {
        return bound * random.nextDouble();
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }

}
