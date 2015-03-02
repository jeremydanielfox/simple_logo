package model.node.mathOperation;

public class Sum extends TwoArgMathOperation {

    public Sum () {
    }

    public double evaluate () {
        return getFirstArg() +  getSecondArg();
    }

}
