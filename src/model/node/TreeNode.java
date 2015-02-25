package model.node;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public abstract class TreeNode {
	
    private Map<String, TreeNode> children = new HashMap<String, TreeNode>();
    private TreeNode neighbor;
    
    public abstract double evaluate();
    
    public Queue<String> getTokenTracker(){
    	return null;
    }
    
    public void addChild(String key, TreeNode child){
        children.put(key, child);
    }
    
    public void setNeighbor(TreeNode node) {
    	neighbor = node;
    }
    
    public TreeNode getNeighbor() {
    	return this.neighbor;
    }
    
    public boolean hasNeighbor(){
    	return (this.getNeighbor() != null);
    }
    
    protected TreeNode getChild(String key){
        return children.get(key);
    }
    
    protected int getChildrenSize(){
        return children.size();
    }
}
