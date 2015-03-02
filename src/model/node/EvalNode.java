package model.node;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public abstract class EvalNode extends TreeNode {

    // TODO: not make childBuilders protected
    private Queue<ChildBuilder> childBuilders = new LinkedList<ChildBuilder>();
    private Map<String, TreeNode> children = new LinkedHashMap<String, TreeNode>();

    public EvalNode () {
        setChildBuilders();
    }

    // hacky: used only for Command so it can set variable amount of children
    public EvalNode (boolean variableChildren) {
    }

    public abstract double evaluate ();
    
    public Class<? extends TreeNode> getNextType () {
        return childBuilders.peek().getType();
    }

    public void addChild (TreeNode node) {
        children.put(childBuilders.poll().getName(), node);
    }

    protected EvalNode getEvalChild (String key) {
        return (EvalNode) children.get(key);
    }

    public boolean allChildrenPresent () {
        return childBuilders.isEmpty();
    }

    // TODO: not make protected?
    protected void setChildBuilders () {
        childBuilders.addAll(Arrays.asList(addChildBuilders()));
    }

    protected abstract ChildBuilder[] addChildBuilders ();

    public Map<String, TreeNode> getChildren () {
        return children;
    }

    public boolean hasChildren () {
        return (children != null);
    }

    public String toString () {
        String[] temp = this.getClass().getName().split("\\.");
        return temp[temp.length - 1];
    }

}
