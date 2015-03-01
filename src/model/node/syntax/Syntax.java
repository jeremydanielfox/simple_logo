package model.node.syntax;

import java.util.Map;

import model.node.TreeNode;

// dummy class, used for error checking
public abstract class Syntax extends TreeNode{
    
	@Override
    public boolean hasChildren(){
    	return false;
    }
    
	@Override
    public Map<String, TreeNode> getChildren(){
    	return null;
    }
}
