package model;

import model.node.TreeNode;


public class Interpreter {

    private TreeNode tree;

    public Interpreter (TreeNode treeInfo) {
        tree = treeInfo;
    }

    // evaluate all Syntax Trees in list
    public void interpret () {
        tree.evaluate();
        if (tree.hasNeighbor()){
            tree = tree.getNeighbor();
            interpret();
        }
    }
}
