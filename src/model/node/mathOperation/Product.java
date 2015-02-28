package model.node.mathOperation;

public class Product extends TwoArgMathOperation {

    public Product () {
    }
    
    @Override
    public double evaluate () {
        return getFirstArg() *  getSecondArg();
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }

}
