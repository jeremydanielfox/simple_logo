package node;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MathOperation extends Node {

    private static Random random = new Random();

    private Map<String, Double> opsMap;
    private String myOp;

    public MathOperation (String op) {
        if (getChildren().size() == 1) {
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
        opsMap.put("~", -getFirstChild().evaluate());
        opsMap.put("rand", nextDouble(getFirstChild().evaluate()));
        opsMap.put("sin", Math.sin(Math.toRadians(getFirstChild().evaluate())));
        opsMap.put("cos", Math.cos(Math.toRadians(getFirstChild().evaluate())));
        opsMap.put("tan", Math.tan(Math.toRadians(getFirstChild().evaluate())));
        opsMap.put("atan", Math.toDegrees(Math.atan(getFirstChild().evaluate())));
        opsMap.put("log", Math.log(getFirstChild().evaluate()));
        opsMap.put("pi", Math.PI);
    }

    private void buildTwoArgsMap () {
        opsMap = new HashMap<String, Double>();
        opsMap.put("+", getFirstChild().evaluate() + getSecondChild().evaluate());
        opsMap.put("-", getFirstChild().evaluate() - getSecondChild().evaluate());
        opsMap.put("*", getFirstChild().evaluate() * getSecondChild().evaluate());
        opsMap.put("/", getFirstChild().evaluate() / getSecondChild().evaluate());
        opsMap.put("%", getFirstChild().evaluate() % getSecondChild().evaluate());
        opsMap.put("pow", Math.pow(getFirstChild().evaluate(), getSecondChild().evaluate()));
        opsMap.put("pi", Math.PI); // duplicate of above
    }

    // implementation of Math's nextDouble with a bound
    private double nextDouble (double bound) {
        return bound * random.nextDouble();
    }

    private Node getFirstChild () {
        return getChildren().get("first");
    }

    private Node getSecondChild () {
        return getChildren().get("second");
    }
}
