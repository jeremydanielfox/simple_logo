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


public final class NodeFactory {

    private static NodeFactory instance;
    private static final Map<Wrapper, List<String>> reflectionMap;
    static
    {
        String[] turtleCmds =
                new String[] { "Forward", "Backward", "Left", "Right", "PenUp", "PenDown", "Home",
                              "ClearScreen", "ShowTurtle", "HideTurtle", "Tell"};
        String [] writerCmds = 
                new String[] {"MakeVariable", "MakeUserInstruction" };
        String[] databaseCmds = 
                new String[] {"Command", "Variable" };
        String[] mathOpCmds = new String[] { "Sum", "Difference", "Product", "Quotient", "Random", "Sine" };
        String[] boolCmds = new String[] { "GreaterThan", "LessThan" };
        String[] ctrlStructCmds =
                new String[] { "Repeat", "DoTimes", "For",  "If", "IfElse" };
        String[] syntaxCmds = new String[] { "ListStart", "ListEnd" };
        String[] basicCmds = new String[] { "Constant" };

        reflectionMap = new HashMap<Wrapper, List<String>>();
        reflectionMap.put(new Wrapper("turtleCommand", new Class<?> [] {Turtle.class}),
                          new ArrayList<String>(Arrays.asList(turtleCmds)));
        reflectionMap.put(new Wrapper("writer", new Class<?> [] {model.database.Writer.class}),
                          new ArrayList<String>(Arrays.asList(writerCmds)));
        reflectionMap.put(new Wrapper("database", new Class<?> [] {String.class, Database.class}),
                          new ArrayList<String>(Arrays.asList(databaseCmds)));
        reflectionMap.put(new Wrapper("basic", new Class<?> [] {String.class}),
                          new ArrayList<String>(Arrays.asList(basicCmds)));
        reflectionMap.put(new Wrapper("mathOperation", new Class<?>[0]),
                          new ArrayList<String>(Arrays.asList(mathOpCmds)));
        reflectionMap.put(new Wrapper("booleanOperation", new Class<?>[0]),
                          new ArrayList<String>(Arrays.asList(boolCmds)));
        reflectionMap.put(new Wrapper("controlStructure", new Class<?>[0]),
                          new ArrayList<String>(Arrays.asList(ctrlStructCmds)));
        reflectionMap.put(new Wrapper("syntax", new Class<?>[0]),
                          new ArrayList<String>(Arrays.asList(syntaxCmds)));
    }

    private NodeFactory () {
    }

    // singleton may be dangerous...
    public static synchronized NodeFactory getInstance () {
        if (instance == null)
            instance = new NodeFactory();
        return instance;
    }

    public static TreeNode get (TokenProperty tokenProp, Workspace workspace) {
        String type = tokenProp.getType();
        String token = tokenProp.getToken();
        Wrapper wrapper = getWrapper(type);

        // TODO: fix so not so messy
        // require token
        if (wrapper.getPackage().equals("basic")) {
            try {
                return reflectionFactory(wrapper.getPackage(), type, wrapper.getArg(), token);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }

        // require turtle
        else if (wrapper.getPackage().equals("turtleCommand")) {
            try {
                return reflectionFactory(wrapper.getPackage(), type, wrapper.getArg(), workspace.getTurtles());
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        
        else if (wrapper.getPackage().equals("writer")){
            try {
                return reflectionFactory(wrapper.getPackage(), type, wrapper.getArg(), workspace.getWriter());
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        
        else if (wrapper.getPackage().equals("database")){
            try {
                return reflectionFactory(wrapper.getPackage(), type, wrapper.getArg(), 
                                         new Object[] {token, workspace.getDatabase()});
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }

        // all other cases
        // TODO: look into oodesign pattern, initializing within the node itself
        else {
            try {
                return reflectionFactory(wrapper.getPackage(), type, wrapper.getArg(), new Object[0]);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }

        }
    }

    private static TreeNode reflectionFactory (String packageName,
                                               String type,
                                               Class<?>[] argType,
                                               Object... arg)
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

    private static Wrapper getWrapper (String type) {
        for (Wrapper w : reflectionMap.keySet()) {
            if (reflectionMap.get(w).contains(type)) { return w; }
        }
        System.err.println("reflection Map error");
        throw new RuntimeException();
    }

    static class Wrapper {
        private String name;
        private Class<?> [] arg;

        public Wrapper (String packageName, Class<?>[] constructorArg) {
            this.name = packageName;
            this.arg = constructorArg;
        }

        public String getPackage () {
            return name;
        }

        public Class<?>[] getArg () {
            return arg;
        }
    }
}
