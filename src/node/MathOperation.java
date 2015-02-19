package node;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MathOperation extends Node {

    private static Random random = new Random();
    
    private Map<String, Double> opsMap;
    private String myOp;
    

    public MathOperation (Node arg1, Node arg2, String op) {
        Collections.addAll(getChildren(), arg1, arg2);
        buildTwoArgsMap();
        myOp = op;
    }
    
    public MathOperation (Node child, String op){
        getChildren().add(child);
        buildOneArgMap();
        myOp = op;
    }

    public double evaluate () {
        return opsMap.get(myOp);
    }
    
    private void buildOneArgMap(){
        opsMap = new HashMap<String, Double>();
        opsMap.put("~", -getChild().evaluate());
        opsMap.put("rand", nextDouble(getChild().evaluate()));
        opsMap.put("sin", Math.sin(Math.toRadians(getChild().evaluate())));
        opsMap.put("cos", Math.cos(Math.toRadians(getChild().evaluate())));
        opsMap.put("tan", Math.tan(Math.toRadians(getChild().evaluate())));
        opsMap.put("atan", Math.toDegrees(Math.atan(getChild().evaluate())));
        opsMap.put("log", Math.log(getChild().evaluate()));
        opsMap.put("pi", Math.PI);
    }

    private void buildTwoArgsMap(){
        opsMap = new HashMap<String, Double>();
        opsMap.put("+", getLeft().evaluate() + getRight().evaluate());
        opsMap.put("-", getLeft().evaluate() - getRight().evaluate());
        opsMap.put("*", getLeft().evaluate() * getRight().evaluate());
        opsMap.put("/", getLeft().evaluate() / getRight().evaluate());
        opsMap.put("%", getLeft().evaluate() % getRight().evaluate());;
        opsMap.put("pow", Math.pow(getLeft().evaluate(), getRight().evaluate()));
        opsMap.put("pi", Math.PI);   
    }
    
    // implementation of Math's nextDouble with a bound
    private double nextDouble(double bound){
        return bound*random.nextDouble();
    }  
    
    private Node getChild(){
        return getChildren().get(0);
    }
    
    private Node getLeft(){
        return getChild();
    }
    
    private Node getRight(){
        return getChildren().get(1);
    }
}
