package model.node;

import java.util.List;
import model.node.database.Variable;

//TODO: decide if should be considered and EvalNode
public class Parameters extends EvalNode {

    private List<Variable> myList;
    
    public Parameters(List<Variable> list){
        myList = list;
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }
    
    public List<Variable> getList () {
        return myList;
    }

    @Override
    public double evaluate () {
        return 0;
    }

    @Override
    protected ChildBuilder[] addChildBuilders () {
        return new ChildBuilder[0];
    }
}
