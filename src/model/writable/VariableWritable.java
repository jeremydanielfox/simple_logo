package model.writable;

import model.node.EvalNode;
import model.node.basic.Constant;



public class VariableWritable extends Writable {

    private String name;
    private EvalNode expression;
    private boolean beingDefined;
    private EvalNode previousExpression;

    public VariableWritable (String name, EvalNode expression) {
        this.name = name;
        this.expression = expression;
        previousExpression = new Constant("0");
    }

    public String getName () {
        return name;
    }
    
    @Override
    public String getValue () {
         return expression.toString();
    }

    public EvalNode getExpression () {
        return expression;
    }
    
    public void setDefiningSignal (boolean bool){
        beingDefined = bool;
    }
    
    public boolean getDefiningSignal () {
        return beingDefined;
    }


}
