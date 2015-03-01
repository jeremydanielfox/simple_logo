package model.node;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public abstract class EvalNode extends TreeNode {

    private Queue<ChildBuilder> childBuilders = new LinkedList<ChildBuilder>();
    private Map<String, TreeNode> children = new LinkedHashMap<String, TreeNode>();

    
    public EvalNode(){
        setChildBuilders();
    }

    public abstract double evaluate ();

    public void addChild (TreeNode node) {
        children.put(childBuilders.poll().getName(), node);
    }

    protected EvalNode getEvalChild (String key) {
        return (EvalNode) children.get(key);
    }

    public boolean allChildrenPresent () {
        return childBuilders.isEmpty();
    }

    public Class<? extends TreeNode> getNextType () {
        return childBuilders.peek().getType();
    }

    private void setChildBuilders(){
        childBuilders.addAll(Arrays.asList(addChildBuilders()));
    }
    
    public Map<String, TreeNode> getChildren(){
    	return children;
    }
    
    public boolean hasChildren(){
    	return (children != null);
    }
    
    public String toString(){
    	return this.getClass().getName();
    }

    protected abstract ChildBuilder[] addChildBuilders () ;

}
