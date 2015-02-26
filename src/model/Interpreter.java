package model;

import java.util.List;
import model.node.TreeNode;

public class Interpreter {

	private List<TreeNode> trees;

	public Interpreter(List<TreeNode> treeInfo){
		trees = treeInfo;
	}

	// evaluate all Syntax Trees in list
	public void interpret () {
//		while (tree.hasNeighbor()){
//			tree.evaluate();
//			tree = tree.getNeighbor();
//		}
	    for (TreeNode tree :trees){
	        tree.evaluate();
	    }
	}	
}

