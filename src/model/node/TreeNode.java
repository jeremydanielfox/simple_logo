package model.node;

import java.util.Map;


// dummy that is the basest type of Node you could have
public abstract class TreeNode {

    public String toString(){
    	return this.toString() + " " + this.childrenToString();
    }
    
    public String childrenToString(){
    	String s = "";
    	if (this.hasChildren()){
    		for (TreeNode tn : this.getChildren().values()){
	    		s += tn.toString();
			} 
    	}
	    return s; 
    }
    
    public abstract boolean hasChildren();
    
    public abstract Map<String, TreeNode> getChildren();
    
}
