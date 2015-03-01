package model.node.mathOperation;

public class Product extends TwoArgMathOperation {

    public Product () {
    }
    
    @Override
    public double evaluate () {
        return getFirstArg() *  getSecondArg();
    }

}
