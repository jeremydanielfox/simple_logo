package node;

import java.util.Map;

public abstract class Node {

    private Map<String, Node> children;
    
    public abstract double evaluate();
    
    public void addChild(String key, Node child){
        children.put(key, child);
    }
    
    protected Map<String, Node> getChildren(){
        return children;
    }
}
