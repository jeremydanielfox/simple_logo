package model.node.mathOperation;

import model.node.TreeNode;

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

    private TreeNode getFirstArg () {
        return getChild("arg1");
    }
    
    private TreeNode getSecondArg () {
        return getChild("arg2");
    }

}
