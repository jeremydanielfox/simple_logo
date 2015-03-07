package model.node;

import java.util.List;

/**
 * The root of any tree. It contains a list of commands to run (e.g. [fd 50, rt 90]). 
 * @author Nathan Prabhu
 *
 */
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
