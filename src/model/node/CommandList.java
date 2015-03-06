package model.node;

import java.util.List;

public class CommandList extends ZeroArgOperation{
    
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

    // toString will be represented by its List
    @Override
    public String toString (){
        String result = "";
        for (EvalNode node : myList){
            result += node.toString();
        }
        return result;
    }
}
