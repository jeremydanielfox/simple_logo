package model;

import java.util.List;
import model.node.TreeNode;


public class Interpreter {

    private List<TreeNode> TreeList;

    // get Syntax Trees in list
    public void interpret () {
        for (TreeNode n : TreeList) {
            n.evaluate();
        }
    }

}
