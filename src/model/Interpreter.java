package model;

import model.node.TreeNode;

public class Interpreter {

	private TreeNode tree;

	public Interpreter(TreeNode treeInfo){
		tree = treeInfo;
	}

	// evaluate all Syntax Trees in list
	public void interpret () {
		while (tree.hasNeighbor()){
			tree.evaluate();
			tree = tree.getNeighbor();
		}
	}	
}

