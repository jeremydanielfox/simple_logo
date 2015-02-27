package model.node.mathOperation;

import model.node.EvalNode;

public class TwoArgMathOperation extends MathOperation{

    public TwoArgMathOperation (String op) {
        super(op);
        addChildNames(new String[] {"arg1", "arg2"});
    }
    @Override
    protected void buildMap () {
        getOpsMap().put("+", getFirstArg().evaluate() + getSecondArg().evaluate());
        getOpsMap().put("-", getFirstArg().evaluate() - getSecondArg().evaluate());
        getOpsMap().put("*", getFirstArg().evaluate() * getSecondArg().evaluate());
        getOpsMap().put("/", getFirstArg().evaluate() / getSecondArg().evaluate());
        getOpsMap().put("%", getFirstArg().evaluate() % getSecondArg().evaluate());
        getOpsMap().put("pow", Math.pow(getFirstArg().evaluate(), getSecondArg().evaluate()));
    }

    private EvalNode getFirstArg () {
        return getChild("arg1");
    }
    
    private EvalNode getSecondArg () {
        return getChild("arg2");
    }

}