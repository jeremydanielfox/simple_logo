package model.node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class TreeNodeInfo {

    private Queue<String> myChildNames;
    private TreeNode myNode;

    public TreeNodeInfo (TreeNode node, String[] childNames) {
        myNode = node;
        if (childNames.length > 0) { // would be for a Constant
            myChildNames = new LinkedList<String>(Arrays.asList(childNames));
        }
        else{
        	myChildNames = new LinkedList<String>();
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
