package model.node;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Parser.TokenProperty;
import model.Workspace;
import model.database.Database;
import model.turtle.Turtle;


/**
 * Singleton class to create a node from the given TokenProperty and workspace
 * 
 * @author Nathan Prabhu
 *
 */

public final class NodeFactory {

    private static NodeFactory instance;

    /**
     * Map to used organize package, constructor argument types, and constructor arguments for each
     * TokenProperty. When new commands are created, one only needs to add their name to the appropriate String[].
     */
    private static final Map<ReflectionWrapper, List<String>> reflectionMap;
    static
    {
        String[] turtleCmds =
                new String[] { "Forward", "Backward", "Left", "Right", "PenUp", "PenDown", "Home",
                              "ClearScreen", "ShowTurtle", "HideTurtle", "SetHeading", "Tell",
                              "Ask" };
        String[] turtleQueries =
                new String[] { "XCoordinate", "YCoordinate", "Heading", "ID" };
        String[] writerCmds =
                new String[] { "MakeVariable", "MakeUserInstruction" };
        String[] databaseCmds =
                new String[] { "Command", "Variable", "Pallet" };
        String[] mathOpCmds =
                new String[] { "Sum", "Difference", "Product", "Quotient", "Random", "Sine" };
        String[] boolCmds = new String[] { "GreaterThan", "LessThan" };
        String[] ctrlStructCmds =
                new String[] { "Repeat", "DoTimes", "For", "If", "IfElse" };
        String[] syntaxCmds = new String[] { "ListStart", "ListEnd" };
        String[] basicCmds = new String[] { "Constant" };

        reflectionMap = new HashMap<ReflectionWrapper, List<String>>();
        reflectionMap.put(new ReflectionWrapper("turtleCommand", new Class<?>[] { Turtle.class },
                                                new int[] { 1 }),
                          new ArrayList<String>(Arrays.asList(turtleCmds)));
        reflectionMap.put(new ReflectionWrapper("turtleQuery", new Class<?>[] { Turtle.class,
                                                                               Database.class },
                                                new int[] { 1, 2 }),
                          new ArrayList<String>(Arrays.asList(turtleQueries)));
        reflectionMap.put(new ReflectionWrapper("writer",
                                                new Class<?>[] { model.database.Writer.class },
                                                new int[] { 3 }),
                          new ArrayList<String>(Arrays.asList(writerCmds)));
        reflectionMap.put(new ReflectionWrapper("database", new Class<?>[] { String.class,
                                                                            Database.class },
                                                new int[] { 0, 2 }),
                          new ArrayList<String>(Arrays.asList(databaseCmds)));
        reflectionMap.put(new ReflectionWrapper("basic", new Class<?>[] { String.class },
                                                new int[] { 0 }),
                          new ArrayList<String>(Arrays.asList(basicCmds)));
        reflectionMap.put(new ReflectionWrapper("mathOperation", new Class<?>[0], new int[0]),
                          new ArrayList<String>(Arrays.asList(mathOpCmds)));
        reflectionMap.put(new ReflectionWrapper("booleanOperation", new Class<?>[0],
                                                new int[0]),
                          new ArrayList<String>(Arrays.asList(boolCmds)));
        reflectionMap.put(new ReflectionWrapper("controlStructure", new Class<?>[0],
                                                new int[0]),
                          new ArrayList<String>(Arrays.asList(ctrlStructCmds)));
        reflectionMap.put(new ReflectionWrapper("syntax", new Class<?>[0], new int[0]),
                          new ArrayList<String>(Arrays.asList(syntaxCmds)));
    }

    private static Object[] args;

    private NodeFactory () {
    }

    public static synchronized NodeFactory getInstance () {
        if (instance == null)
            instance = new NodeFactory();
        return instance;
    }

    /**
     * The main method used to generate a TreeNode. The TokenProperty is the key and the Workspace
     * offers potential constructor arguments for each of the nodes.
     * 
     * @param tokenProp
     * @param workspace
     * @return
     */
    public static TreeNode get (TokenProperty tokenProp, Workspace workspace) {
        String type = tokenProp.getType();
        String token = tokenProp.getToken();
        ReflectionWrapper wrapper = getWrapper(type);

        args =
                new Object[] { token, workspace.getTurtles(), workspace.getDatabase(),
                              workspace.getWriter() };

        try {
            return reflectionFactory(wrapper.getPackage(), type, wrapper.getArgType(),
                                     getArgs(wrapper.getArgIndices()));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private static Object[] getArgs (int[] indices) {
        List<Object> result = new ArrayList<Object>();
        for (int i : indices) {
            result.add(args[i]);
        }
        return result.toArray();
    }

    private static TreeNode reflectionFactory (String packageName,
                                               String type,
                                               Class<?>[] argType,
                                               Object ... arg)
                                                              throws Exception {
        try {
            Class<?> targetClass =
                    Class.forName(String.format("model.node.%s.%s", packageName, type));
            try {
                Constructor<?> constructor = targetClass.getConstructor(argType);
                return (TreeNode) constructor.newInstance(arg);

            }
            catch (NoSuchMethodException | SecurityException | InvocationTargetException e) {
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

    private static ReflectionWrapper getWrapper (String type) {
        for (ReflectionWrapper w : reflectionMap.keySet()) {
            if (reflectionMap.get(w).contains(type)) { return w; }
        }
        System.err.println("reflection Map error");
        throw new RuntimeException();
    }

    /**
     * A wrapper used as a value in the reflection map
     * @author Nathan Prabhu
     *
     */
    private static class ReflectionWrapper {
        private String name;
        private Class<?>[] arg;
        private int[] argIndices;

        private ReflectionWrapper (String packageName, Class<?>[] constructorArg, int[] argIndices) {
            this.name = packageName;
            this.arg = constructorArg;
            this.argIndices = argIndices;
        }

        private String getPackage () {
            return name;
        }

        private Class<?>[] getArgType () {
            return arg;
        }

        private int[] getArgIndices () {
            return argIndices;
        }
    }
}
