package model.node;

import java.util.Map;


// dummy that is the basest type of Node you could have
public abstract class TreeNode {

    public abstract String toString();
    
    public abstract boolean hasChildren();
    
    public abstract Map<String, TreeNode> getChildren();
    
}
