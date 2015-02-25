package model.node.mathOperation;

public class ZeroArgMathOperation extends MathOperation {

    public ZeroArgMathOperation (String op) {
        super(op);
    }

    @Override
    protected void buildMap () {
        getOpsMap().put("pi", Math.PI);
    }

}
