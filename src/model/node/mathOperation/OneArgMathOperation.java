package model.node.mathOperation;

import java.util.Random;
import model.node.TreeNode;

public class OneArgMathOperation extends MathOperation {
    
    private static Random random = new Random();

    public OneArgMathOperation (String op) {
        super(op);
        addChildNames(new String[] {"arg"});
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void buildMap () {
        getOpsMap().put("~", -getArg().evaluate());
        getOpsMap().put("rand", nextDouble(getArg().evaluate()));
        getOpsMap().put("sin", Math.sin(Math.toRadians(getArg().evaluate())));
        getOpsMap().put("cos", Math.cos(Math.toRadians(getArg().evaluate())));
        getOpsMap().put("tan", Math.tan(Math.toRadians(getArg().evaluate())));
        getOpsMap().put("atan", Math.toDegrees(Math.atan(getArg().evaluate()))); // throw appropriate error
        getOpsMap().put("log", Math.log(getArg().evaluate())); // throw appropriate error       
    }
    
    // implementation of Math's nextDouble with a bound
    private double nextDouble (double bound) {
        return bound * random.nextDouble();
    }
    
    private TreeNode getArg () {
        return getChild("arg");
    }

}
