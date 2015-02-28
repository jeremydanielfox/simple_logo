package model.node;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class EvalNode {
	
    private Map<String, EvalNode> children = new LinkedHashMap<String, EvalNode>();
    private Deque<String> myChildNames = new LinkedList<String>();
    private EvalNode neighbor;
    
    public abstract double evaluate();
    
    public Queue<String> getTokenTracker(){
    	return new LinkedList<String>();
    }
    
    public void setNeighbor(EvalNode node) {
    	neighbor = node;
    }
    
    public EvalNode getNeighbor() {
    	return this.neighbor;
    }
    
    public boolean hasNeighbor(){
    	return (this.getNeighbor() != null);
    }
    
    public void addChild (EvalNode node) {
        children.put(myChildNames.poll(), node);
    }
    
    protected EvalNode getChild(String key){
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
    
    //public abstract String toString ();
}
