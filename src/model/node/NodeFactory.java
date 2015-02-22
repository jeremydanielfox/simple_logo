package model.node;

public class NodeFactory {

    public NodeFactory () {

    }

    // instantiates TreeNodes
    public TreeNode get (String key) {
        switch (key) {
            case "Forward":
                return new Translation(true);
            case "Sum":
                return new MathOperation("+");
            case "Repeat":
                return new Iteration();
            default:
                // throw "key not found" exception... shouldn't happen though
                return null;
        }
    }
}
