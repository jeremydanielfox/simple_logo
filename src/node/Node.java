package node;

import java.util.List;

public abstract class Node {

    private List<Node> children;
    
    public abstract double evaluate();
    
    public void addChild(Node child){
        children.add(child);
    }
    
    protected List<Node> getChildren(){
        return children;
    }
}
