package model.node;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Parser.TokenProperty;
import model.turtle.SingleTurtle;


public final class NodeFactory {

    private static NodeFactory instance;
    private static final Map<Wrapper, List<String>> reflectionMap;
    static
    {
        String[] turtleCmds =
                new String[] { "Forward", "Backward", "Left", "Right", "PenUp", "PenDown", "Home",
                              "ClearScreen", "ShowTurtle", "HideTurtle" };
        String[] mathOpCmds = new String[] { "Sum", "Difference", "Product", "Quotient", "Random", "Sine" };
        String[] boolCmds = new String[] { "GreaterThan", "LessThan" };
        String[] ctrlStructCmds =
                new String[] { "Repeat", "DoTimes", "For", "MakeVariable", "MakeUserInstruction",
                              "If", "IfElse" };
        String[] syntaxCmds = new String[] { "ListStart", "ListEnd" };
        String[] basicCmds = new String[] { "Constant", "Variable", "Command" };

        reflectionMap = new HashMap<Wrapper, List<String>>();
        reflectionMap.put(new Wrapper("turtleCommand", new Class<?> [] {SingleTurtle.class}),
                          new ArrayList<String>(Arrays.asList(turtleCmds)));
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

    public static TreeNode get (TokenProperty tokenProp, SingleTurtle turtle) {
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
                return reflectionFactory(wrapper.getPackage(), type, wrapper.getArg(), turtle);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }

        // all other cases
        // TODO: fix - last argument not necessary
        // now have two factories
        // TODO: look into oodesign pattern, intializing within the node itself
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
