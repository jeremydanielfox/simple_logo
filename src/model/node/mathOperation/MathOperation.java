package model.node.mathOperation;

import java.util.HashMap;
import java.util.Map;
import model.node.EvalNode;


public abstract class MathOperation extends EvalNode {

    private Map<String, Double> myOpsMap = new HashMap<String, Double>();
    private String myOp;

    public MathOperation (String op) {
        myOp = op;
    }

    public double evaluate () {
        buildMap();
        return myOpsMap.get(myOp);
    }
    
    protected abstract void buildMap();

    protected Map<String, Double> getOpsMap () {
        return myOpsMap;
    }
}