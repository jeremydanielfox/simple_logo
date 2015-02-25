package model.node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class TreeNode {
	
    private Map<String, TreeNode> children = new HashMap<String, TreeNode>();
    private Queue<String> myChildNames = new LinkedList<String>();
    private TreeNode neighbor;
    
    public abstract double evaluate();
    
    public Queue<String> getTokenTracker(){
    	return null;
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
    
    public void addChild (TreeNode node) {
        children.put(myChildNames.poll(), node);
    }
    
    protected TreeNode getChild(String key){
        return children.get(key);
    }
    
    protected int getChildrenSize(){
        return children.size();
    }
    
    protected void setChildNames(String[] names){
        myChildNames.addAll(Arrays.asList(names)); 
    }
    
    public String getNextChildName () {
        return myChildNames.remove();
    }

    public boolean allChildrenPresent () {
        return myChildNames.isEmpty();
    }
}
