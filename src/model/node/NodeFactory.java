package model.node;

import model.Turtle;
import model.node.mathOperation.TwoArgMathOperation;

public final class NodeFactory {

    private static NodeFactory instance;

    private NodeFactory () {
    }

    // singleton may be dangerous... 
    public static synchronized NodeFactory getInstance () {
        if (instance == null)
            instance = new NodeFactory();
        return instance;
    }

    // unfortunate that we have to use case/switch for every command. But alternative would be to
    // create unnecessary amount of classes, if reflection or oodesign factory pattern were used
    public TreeNode getNonConstant (String key, Turtle turtle) {
        switch (key) {
            case "Forward":
                return new Translation(true, turtle);
            case "Sum":
                return new TwoArgMathOperation("+");
            case "Repeat":
                return new Iteration();
            default:
                // throw "key not found" exception... shouldn't happen though
                return null;
        }
    }

    // separate parameter needed for constants, should probably refactor
    public TreeNode getConstant (double value) {
        return new Constant(value);
    }
}
