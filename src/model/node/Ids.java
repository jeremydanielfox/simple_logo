package model.node;

import java.util.List;
import model.node.database.Variable;

public class Ids extends EvalNode {

 private List<Integer> myList;
    
    public Ids(List<Integer> list){
        myList = list;
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }
    
    public List<Integer> getList () {
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
