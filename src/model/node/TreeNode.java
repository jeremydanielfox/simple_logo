package model.node;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class TreeNode {
	
    private Map<String, TreeNode> children = new HashMap<String, TreeNode>();
    private Deque<String> myChildNames = new LinkedList<String>();
    private TreeNode neighbor;
    
    public abstract double evaluate();
    
    public Queue<String> getTokenTracker(){
    	return new LinkedList<String>();
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
    
    protected void addChildNames(String[] names){
        myChildNames.addAll(Arrays.asList(names)); 
    }
    
    // used specifically in DoTimes
    protected void addChildNameFirst(String name){
        myChildNames.addFirst(name);
    }
    
    public String getNextChildName () {
        return myChildNames.remove();
    }

    public boolean allChildrenPresent () {
        return myChildNames.isEmpty();
    }
}
