package model.node;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Turtle;
import model.node.iteration.Repeat;
import model.node.mathOperation.TwoArgMathOperation;
import model.node.turtleCommand.Forward;
import model.node.turtleCommand.Rotation;
import model.node.turtleCommand.Translation;


public final class NodeFactory {

    private static NodeFactory instance;
    private List<String> turtleCommands = new ArrayList<String>(Arrays.asList(new String[]{
                                                                                           "Forward",
                                                                                           "Backward",
                                                                                           "Left",
                                                                                           "Right"
    }));

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
        if (isTurtleCommand(key)) { 
            try {
                return reflectionFactory(key, turtle);
            }
            catch (Exception e) {
                // not sure how to handle this one
                e.printStackTrace();
                throw new RuntimeException();
            }
        }

        switch (key) {
            case "Sum":
                return new TwoArgMathOperation("+");
            case "Repeat":
                return new Repeat();
            default:
                // throw "key not found" exception... shouldn't happen though
                return null;
        }
    }

    private boolean isTurtleCommand (String key) {
        return turtleCommands.contains(key);
    }

    private TreeNode reflectionFactory (String key, Turtle turtle) throws Exception {
        try {
            Class<?> targetClass = Class.forName(String.format("model.node.turtleCommand.%s", key));
            try {
                Constructor<?> constructor = targetClass.getConstructor(Turtle.class);
                return (TreeNode) constructor.newInstance(turtle);
            }
            catch (NoSuchMethodException | SecurityException e) {
                System.err.println("incorrect constructor");
                e.printStackTrace();
                throw new RuntimeException(); // do something other than throw error to stop program
            }
        }
        catch (ClassNotFoundException e) {
            System.err.println("TurtleEnum error");
            e.printStackTrace();
            throw new RuntimeException(); // do something other than throw error to stop program
        }
    }

    // separate parameter needed for constants, should probably refactor
    public TreeNode getConstant (double value) {
        return new Constant(value);
    }
}
