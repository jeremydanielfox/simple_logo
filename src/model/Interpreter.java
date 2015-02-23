package model;

import java.util.List;
import model.node.TreeNode;


public class Interpreter {

    private List<TreeNode> TreeList;

    // evaluate all Syntax Trees in list
    public void interpret (List<TreeNode> list) {
        for (TreeNode n : TreeList) {
            n.evaluate();
        }
    }

}
