package model;

import java.util.List;
import model.node.TreeNode;


public class Interpreter {

    private List<TreeNode> treeList;
    
    public Interpreter(List<TreeNode> trees){
        treeList = trees;
    }

    // evaluate all Syntax Trees in list
    public void interpret () {
        for (TreeNode n : treeList) {
            n.evaluate();
        }
        
    }

}
