package model;

import java.util.List;
import model.node.TreeNode;


public class Interpreter {

    private TreeNode tree;

    public Interpreter (TreeNode treeInfo) {
        tree = treeInfo;
    }

    // evaluate all Syntax Trees in list
    public void interpret () {
    	//System.out.println(tree.toString());
        tree.evaluate();
        if (tree.hasNeighbor()){
            tree = tree.getNeighbor();
            interpret();
        }
    }
}
