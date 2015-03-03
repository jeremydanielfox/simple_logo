package model.writable;

import model.node.EvalNode;


public class VariableWritable extends Writable {

    private String name;
    private EvalNode expression;

    public VariableWritable (String name, EvalNode expression) {
        this.name = name;
        this.expression = expression;
    }

    public String getName () {
        return name;
    }
    
    @Override
    public String getValue () {
        // return expression.toString;
        return null;
    }

    public EvalNode getExpression () {
        return expression;
    }


}
