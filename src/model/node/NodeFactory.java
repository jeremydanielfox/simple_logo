package model.node;

import java.util.Arrays;
import model.Turtle;
import model.node.iteration.Repeat;
import model.node.mathOperation.TwoArgMathOperation;
import model.node.turtleCommand.Translation;

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
    
    public enum TurtleEnum {
        FORWARD, BACKWARD, LEFT, RIGHT;
    }

    // unfortunate that we have to use case/switch for every command. But alternative would be to
    // create unnecessary amount of classes, if reflection or oodesign factory pattern were used
    public TreeNode getNonConstant (String key, Turtle turtle) {
        if (Arrays.asList(TurtleEnum.values()).contains(TurtleEnum.valueOf(key))){
            // do reflection
        }
        
        switch (key) {
            case "Forward":
                return new Translation(true, turtle);
            case "Backward":
                return new Translation(false, turtle);
            case "Left":
                //return new Rotation(false, turtle);
            case "Sum":
                return new TwoArgMathOperation("+");
            case "Repeat":
                return new Repeat();
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
