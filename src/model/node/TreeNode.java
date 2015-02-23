package model.node;

import java.util.HashMap;
import java.util.Map;

public abstract class TreeNode {

    private Map<String, TreeNode> children = new HashMap<String, TreeNode>();
    
    public abstract double evaluate();
    
    public void addChild(String key, TreeNode child){
        children.put(key, child);
    }
    
    protected TreeNode getChild(String key){
        return children.get(key);
    }
    
    protected int getChildrenSize(){
        return children.size();
    }
}
