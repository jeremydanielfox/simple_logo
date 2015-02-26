package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import model.database.Database;
import model.node.NodeFactory;
import model.node.TreeNode;
import model.node.iteration.DoTimes;
import model.node.iteration.For;
import Exceptions.CommandNotFoundException;
import Exceptions.UnexpectedEndOfInstructionsException;
import Exceptions.VariableNotFoundException;


public class Parser {

    private List<Entry<String, Pattern>> myPatterns;
    private Turtle myTurtle;
    private Deque<String[]> tokenProperties;
    private Queue<String> tokenTracker = new LinkedList<String>();
    private Stack<String> openBrackets;

    public Parser (List<Entry<String, Pattern>> patterns, Turtle turtle) {
        myPatterns = patterns;
        myTurtle = turtle;
    }

    public List<TreeNode> parse (String feed) {
        List<String> tokens = Arrays.asList(feed.split("\\p{Z}"));

        // read Resource Bundle, convert tokens to Deque
        tokenProperties = new LinkedList<String[]>(tokens.stream()
                .map(this::getMatch)
                .collect(Collectors.toList()));
        return buildTrees();
    }

    private List<TreeNode> buildTrees () {
        // build trees for all tokenProperties
        
//        TreeNode root = null;
//        TreeNode last = null;
//        boolean needRoot = true;
        
        List<TreeNode> result = new ArrayList<TreeNode>() ;
        while (!tokenProperties.isEmpty()) {

            // TODO: must handle list starts/ends for iterators/conditionals
            // -- recognize them (error-check) but not put in tree

            // appropriate node generated using tokenProperties
            TreeNode node = getNextNode();

            addChildren(node);

            // node will have all children
            
            // used for Neighbors implementation
//            if (needRoot) {
//                root = node;
//                last = node;
//                needRoot = false;
//            }
//            else {
//                last.setNeighbor(node);
//                last = last.getNeighbor();
//            }
            
              result.add(node);  
        }
        
        return result;
    }

    private void addChildren (TreeNode node) {
        if (node.allChildrenPresent()) { return; }
        if (tokenProperties.isEmpty()) { throw new UnexpectedEndOfInstructionsException();
        // -- e.g. fd sum 50
        }
        TreeNode childNode = getNextNode();
        addChildren(childNode);

        if (!tokenTracker.isEmpty() && (node instanceof DoTimes || node instanceof For)) {
            tokenTracker.poll();
        }
        node.addChild(childNode);
        return;
    }

    private TreeNode getNextNode () {
        String[] tokenProp = getNextTokenProperty();
        TreeNode tempNode;
        // special case of creating a constant node
        if (tokenProp[0].equals("Constant")) {
            tempNode = NodeFactory.getInstance().getConstant(Double.parseDouble(tokenProp[1]));
        }
        else {
            tempNode = NodeFactory.getInstance().getNonConstant(tokenProp[0], myTurtle);
        }
        tokenTracker = tempNode.getTokenTracker();
        return tempNode;
    }

    private String[] getNextTokenProperty () {
        String[] tokenProp = tokenProperties.poll();

        if (tokenProp[0].equals("Comment")) {
            // TODO: recognize comments, must know when lines end
            return getNextTokenProperty();
        }
        else if (tokenProp[0].equals("MakeVariable") || tokenProp[0].equals("MakeUserInstruction")) {
            // handle making new variable or udc
            // -- use [ ] as ending conditions
            return getNextTokenProperty();
        }
        else if (tokenProp[0].equals("Variable")) {
            // check database if variable/udc exists, replace with value

            if (Database.getInstance().getVariable(tokenProp[1]) == null) {
                throw new VariableNotFoundException();
            }
            else {
                // -- use deque functionality: addFirst

                // TODO refactor how we testForVars within brackets
                testForVar();
            }

            return getNextTokenProperty();
        }
        else if (tokenProp[0].equals("Command")) {
            // check database if variable/udc exists, replace with value
            // -- use deque functionality: addFirst
            // otherwise throw new CommandNotFoundException();
            return getNextTokenProperty();
        }
        else if (tokenProp[0].equals("ListStart")) {
            // do something... should apply to MakeUserInstructions, iterators, conditionals
            testForOpenBracket();
            openBrackets.push("Open");
            return null;
        }
        else if (tokenProp[0].equals("ListEnd")) {
            // do something... should apply to MakeUserInstructions, iterators, conditionals
            testForClosedBracket();
            openBrackets.pop(); // need
            return null;
        }
        else {
            return tokenProp;
        }
    }

    private String[] getMatch (String token) {
        boolean matched = false;
        if (token.trim().length() > 0) {
            for (Entry<String, Pattern> p : myPatterns) {
                if (match(token, p.getValue())) {
                    // System.out.println(String.format("%s matches %s", token, p.getKey()));
                    matched = true;
                    return new String[] { p.getKey(), token }; // need token for Constant, Variable,
                                                               // Command
                }
            }
            if (!matched) {
                // throw new UnrecognizedTokenException()
                // would be called if regex couldnt classify command
                System.out.println(String.format("%s not matched", token));
            }
        }
        // should never get to this point...
        return null;
    }

    private boolean match (String input, Pattern regex) {
        return regex.matcher(input).matches();
    }

    private void testForOpenBracket () {
        if (!tokenTracker.poll().equals("ListStart")) {
            // Throw incorrect input error
        }
    }

    private void testForClosedBracket () {
        if (!tokenTracker.poll().equals("ListEnd")) {
            // Throw incorrect input error
        }
    }

    private void testForVar () {
        if (!tokenTracker.poll().equals("Variable")) {
            // Throw incorrect input error
        }
    }

    private void testForOther () {
        String token = tokenTracker.poll();
        if (token.equals("ListStart") || token.equals("ListEnd") || token.equals("Variable"))
        ;
    }

    protected int multiply (int x, int y) {
        return x * y;
    }
}
