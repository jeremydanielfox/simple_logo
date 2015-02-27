package model;

import java.util.List;
import model.node.EvalNode;


public class Interpreter {

    private EvalNode tree;

    public Interpreter (EvalNode treeInfo) {
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
