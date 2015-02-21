package model;

import java.util.List;
import model.node.Node;


public class Interpreter {

    private List<Node> TreeList;

    // get Syntax Trees in list
    public void interpret () {
        for (Node n : TreeList) {
            n.evaluate();
        }
    }

}
