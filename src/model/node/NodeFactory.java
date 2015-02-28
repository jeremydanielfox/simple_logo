package model.node;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import model.Turtle;


public final class NodeFactory {

    private static NodeFactory instance;
    private static final Map<String, String> packageMap;
    static
    {
        packageMap = new HashMap<String, String>();
        packageMap.put("Forward", "turtleCommand");
        packageMap.put("Backward", "turtleCommand");
        packageMap.put("Left", "turtleCommand");
        packageMap.put("Right", "turtleCommand");
        packageMap.put("Repeat", "iteration");
        packageMap.put("ListStart", "syntax");
        packageMap.put("ListEnd", "syntax");

    }

    private NodeFactory () {
    }

    // singleton may be dangerous...
    public static synchronized NodeFactory getInstance () {
        if (instance == null)
            instance = new NodeFactory();
        return instance;
    }

    public static TreeNode get (String[] tokenProperty, Turtle turtle) {
        String type = tokenProperty[0];
        String token = tokenProperty[1];
        if (type.equals("Constant") || type.equals("Variable") || type.equals("Command")) {
            return getTokenNodes(token);
            // call some method that deals with tokenProperty
            // return new Constant(0);
        }
        else {
            // needs only type;

            // get correct package for the following

            // use map to determine:
            // mathOperations
            // turtleCommands
            // Iteration
            // booleans
            // conditionals
            // etc.

            try {
                return reflectionFactory(packageMap.get(type), type, turtle);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    private static TreeNode reflectionFactory (String packageName, String type, Turtle turtle)
                                                                                       throws Exception {
        try {
            Class<?> targetClass =
                    Class.forName(String.format("model.node.%s.%s", packageName, type));
            try {
                if (packageName.equals("turtleCommand")){
                    Constructor<?> constructor = targetClass.getConstructor(Turtle.class);
                    return (TreeNode) constructor.newInstance(turtle);
                }
                else{
                    Constructor<?> constructor = targetClass.getConstructor();
                    return (TreeNode) constructor.newInstance();
                }
            }
            catch (NoSuchMethodException | SecurityException e) {
                System.err.println("incorrect constructor");
                e.printStackTrace();
                throw new RuntimeException(); // do something other than throw error to stop program
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(); // do something other than throw error to stop program
        }
    }

    // separate parameter needed for constants, should probably refactor
    public static TreeNode getTokenNodes (String value) {
        return new Constant(Double.parseDouble(value));
    }
}
