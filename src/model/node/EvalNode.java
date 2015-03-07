package model.node;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


/**
 * Any TreeNode that can be evaluated. As such, they must have children as well.
 * 
 * @author Nathan Prabhu
 *
 */
public abstract class EvalNode extends TreeNode {

    // TODO: not make childBuilders protected
    private Queue<ChildBuilder> childBuilders = new LinkedList<ChildBuilder>();
    private Map<String, TreeNode> children = new LinkedHashMap<String, TreeNode>();

    public EvalNode () {
        setChildBuilders();
    }

    // used only for Command so it can set variable amount of children
    public EvalNode (boolean variableChildren) {
    }

    /**
     * Main function that evaluates EvalNodes
     * 
     * @return double return value
     */
    public abstract double evaluate ();

    /**
     * Gets next expected child's class-type
     * 
     * @return class-type
     */
    public Class<? extends TreeNode> getNextType () {
        return childBuilders.peek().getType();
    }

    public void addChild (TreeNode node) {
        children.put(childBuilders.poll().getName(), node);
    }

    protected EvalNode getEvalChild (String key) {
        return (EvalNode) children.get(key);
    }

    protected TreeNode getChild (String key) {
        return children.get(key);
    }

    public boolean allChildrenPresent () {
        return childBuilders.isEmpty();
    }

    /**
     * Key method for instantiating evalnodes. Will add all childbuilders to childbuilder's map,
     * allowing each EvalNode to know what children it's expecting.
     */
    protected void setChildBuilders () {
        childBuilders.addAll(Arrays.asList(addChildBuilders()));
    }

    /**
     * Defines the ChildBuilders expected for each EvalNode
     * @return
     */
    protected abstract ChildBuilder[] addChildBuilders ();

    public Map<String, TreeNode> getChildren () {
        return children;
    }

    public String toString () {
        String[] temp = this.getClass().getName().split("\\.");
        return temp[temp.length - 1] + " " + childrenToString();
    }

    public String childrenToString () {
        String result = "";
        for (String child : children.keySet()) {
            result += children.get(child).toString();
        }
        return result;
    }

}
