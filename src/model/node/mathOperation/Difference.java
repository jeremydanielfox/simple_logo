package model.node.mathOperation;

public class Difference extends TwoArgMathOperation {
    
    public Difference () {
    }

    @Override
    public double evaluate () {
        return getFirstArg() -  getSecondArg();
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }

    
}
