package model.node;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MathOperation extends Node {

    private static Random random = new Random();

    private Map<String, Double> opsMap;
    private String myOp;

    public MathOperation (String op) {
        if (getChildrenSize() == 1) {
            buildOneArgMap();
        }
        else {
            buildTwoArgsMap();
        }
        myOp = op;
    }

    public double evaluate () {
        return opsMap.get(myOp);
    }

    private void buildOneArgMap () {
        opsMap = new HashMap<String, Double>();
        opsMap.put("~", -getFirstArg().evaluate());
        opsMap.put("rand", nextDouble(getFirstArg().evaluate()));
        opsMap.put("sin", Math.sin(Math.toRadians(getFirstArg().evaluate())));
        opsMap.put("cos", Math.cos(Math.toRadians(getFirstArg().evaluate())));
        opsMap.put("tan", Math.tan(Math.toRadians(getFirstArg().evaluate())));
        opsMap.put("atan", Math.toDegrees(Math.atan(getFirstArg().evaluate())));
        opsMap.put("log", Math.log(getFirstArg().evaluate()));
        opsMap.put("pi", Math.PI);
    }

    private void buildTwoArgsMap () {
        opsMap = new HashMap<String, Double>();
        opsMap.put("+", getFirstArg().evaluate() + getSecondArg().evaluate());
        opsMap.put("-", getFirstArg().evaluate() - getSecondArg().evaluate());
        opsMap.put("*", getFirstArg().evaluate() * getSecondArg().evaluate());
        opsMap.put("/", getFirstArg().evaluate() / getSecondArg().evaluate());
        opsMap.put("%", getFirstArg().evaluate() % getSecondArg().evaluate());
        opsMap.put("pow", Math.pow(getFirstArg().evaluate(), getSecondArg().evaluate()));
        opsMap.put("pi", Math.PI); // duplicate of above
    }

    // implementation of Math's nextDouble with a bound
    private double nextDouble (double bound) {
        return bound * random.nextDouble();
    }

    private Node getFirstArg () {
        return getChild("first");
    }

    private Node getSecondArg () {
        return getChild("second");
    }
}
