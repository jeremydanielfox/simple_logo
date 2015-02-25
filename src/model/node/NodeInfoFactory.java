package model.node;

import model.Turtle;
import model.node.mathOperation.MathOperation;

public final class NodeInfoFactory {

    private static NodeInfoFactory instance;

    private NodeInfoFactory () {
    }

    // singleton may be dangerous... 
    public static synchronized NodeInfoFactory getInstance () {
        if (instance == null)
            instance = new NodeInfoFactory();
        return instance;
    }

    // unfortunate that we have to use case/switch for every command. But alternative would be to
    // create unnecessary amount of classes, if reflection or oodesign factory pattern were used
    public TreeNodeInfo getNonConstant (String key, Turtle turtle) {
        switch (key) {
            case "Forward":
                return new TreeNodeInfo(new Translation(true, turtle), new String[] { "distance" });
            case "Sum":
                return new TreeNodeInfo(new MathOperation("+"), new String[] { "arg1", "arg2" });
            case "Repeat":
                return new TreeNodeInfo(new Iteration(), new String[] { "max" });
            default:
                // throw "key not found" exception... shouldn't happen though
                return null;
        }
    }

    // separate parameter needed for constants, should probably refactor
    public TreeNodeInfo getConstant (double value) {
        return new TreeNodeInfo(new Constant(value), new String[0]);
    }
}
