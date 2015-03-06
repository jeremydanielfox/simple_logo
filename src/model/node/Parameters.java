package model.node;

import java.util.List;
import java.util.stream.Collectors;
import model.node.database.Variable;

//TODO: decide if should be considered and EvalNode
public class Parameters extends TreeNode {

    private List<? extends EvalNode> myList;
    
    public Parameters(List list){
        myList = list;
    }

    @Override
    public String toString () {
        String result = "";
        List<String> params = myList.stream().map(var -> var.toString()).collect(Collectors.toList());
        for (String param : params){
            result += String.format("%s", param);
        }
        return result;
    }
    
    public List getList () {
        return myList;
    }
}
