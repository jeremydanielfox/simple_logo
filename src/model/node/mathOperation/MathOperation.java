package model.node.mathOperation;

import java.util.HashMap;
import java.util.Map;
import model.node.TreeNode;


public abstract class MathOperation extends TreeNode {

    private Map<String, Double> myOpsMap = new HashMap<String, Double>();
    private String myOp;

    public MathOperation (String op) {
        buildMap();
        myOp = op;
    }

    public double evaluate () {
        return myOpsMap.get(myOp);
    }
    
    protected abstract void buildMap();

    protected Map<String, Double> getOpsMap () {
        return myOpsMap;
    }
}