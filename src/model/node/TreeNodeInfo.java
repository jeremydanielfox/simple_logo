package model.node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class TreeNodeInfo {

    private NodeFactory nodeFactory = new NodeFactory();
    private Queue<String> myChildNames;
    private TreeNode myNode;

    public TreeNodeInfo (String key, String[] childNames) {
        if (!key.equals("Constant")) { // would be for a constant
            myNode = nodeFactory.get(key);
        }
        if (childNames.length > 0) { // would be for a Constant
            myChildNames = new LinkedList<String>(Arrays.asList(childNames));
        }
    }

    // should only be set when a Constant needs to be initialized... to be refactored later
    public void setConstant (TreeNode node) {
        if (node instanceof Constant) {
            myNode = node;
        }
    }

    public TreeNode getNode () {
        return myNode;
    }

    public String getNextChildName () {
        return myChildNames.remove();
    }

    public void addChild (TreeNode node) {
        myNode.addChild(myChildNames.poll(), node);
    }

    public boolean allChildrenPresent () {
        return myChildNames.isEmpty();
    }

}
