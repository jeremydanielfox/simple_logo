package model.node;

import java.util.List;

public class CommandList extends EvalNode {
    
    List<EvalNode> myList;
    
    public CommandList(List<EvalNode> list){
        myList = list;
    }

    @Override
    public double evaluate () {
        double lastEvaluated = 0;
        for (EvalNode node: myList){
            lastEvaluated = node.evaluate();
        }
        return lastEvaluated;
    }

    // toString will be represented by its children
    @Override
    public String toString (){
        return "";
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[0];
    }

}