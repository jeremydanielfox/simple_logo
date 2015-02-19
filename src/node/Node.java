package node;

import java.util.List;

public abstract class Node {

    private List<Node> children;
    
    public abstract double evaluate();
    
    protected List<Node> getChildren(){
        return children;
    }
}
