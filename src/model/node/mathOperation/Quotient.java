package model.node.mathOperation;

public class Quotient extends TwoArgMathOperation {

    @Override
    public double evaluate () {
        return getFirstArg() / getSecondArg();
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }

}
