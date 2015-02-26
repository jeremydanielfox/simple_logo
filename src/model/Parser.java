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
import Exceptions.IncorrectNumberOfBracketsException;


public class Parser {

    private List<Entry<String, Pattern>> myPatterns;
    private Turtle myTurtle;
    private Queue<TreeNode> nodeList;
    private Queue<String> tokenTracker = new LinkedList<String>();
    private Stack<String> openBrackets = new Stack<String>();

    public Parser (List<Entry<String, Pattern>> patterns, Turtle turtle) {
        myPatterns = patterns;
        myTurtle = turtle;
    }

    public TreeNode parse (String feed) {
    	//TODO: change to get rid of comments
        List<String> tokens = Arrays.asList(feed.trim().split("\\W+")); //"\\p{Z}"  //"\\W+"
        
        // read Resource Bundle, convert tokens to Deque
        nodeList = new LinkedList<TreeNode>(tokens.stream()
                .map(this::getMatch)
                .collect(Collectors.toList()));
        return buildTrees();
    }

    private TreeNode buildTrees () {
        // build trees for all tokenProperties
        TreeNode root = null;
        TreeNode last = null;
        boolean needRoot = true;
        while (!nodeList.isEmpty()) {

            // TODO: must handle list starts/ends for iterators/conditionals
            // -- recognize them (error-check) but not put in tree

            // appropriate node generated using tokenProperties
            TreeNode node = getNextNode();

            addChildren(node);

            // node will have all children
            if (needRoot) {
                root = node;
                last = node;
                needRoot = false;
            }
            else {
                last.setNeighbor(node);
                last = last.getNeighbor();
            }
        }
        return root;
    }

    private void addChildren (TreeNode node) {
        if (node.allChildrenPresent()) { return; }
        if (nodeList.isEmpty()) { throw new UnexpectedEndOfInstructionsException();
        // -- e.g. fd sum 50
        }
        TreeNode childNode = getNextNode();
        addChildren(childNode);

        node.addChild(childNode);
        return;
    }

    private TreeNode getNextNode () {
//        String[] tokenProp = getNextTokenProperty();
//        TreeNode tempNode;
//        // special case of creating a constant node
//        if (tokenProp[0].equals("Constant")) {
//            tempNode = NodeFactory.getInstance().getConstant(Double.parseDouble(tokenProp[1]));
//        }
//        else {
//            tempNode = NodeFactory.getInstance().getNonConstant(tokenProp[0], myTurtle);
//        }
//        tokenTracker = tempNode.getTokenTracker();
//        return tempNode;
    	return nodeList.poll();
    }

    // TODO: refactor so not to have so many if-statements
//    private String[] getNextTokenProperty () {
//        String[] tokenProp = nodeList.poll();
//        System.out.println(tokenProp[0]);
//        if (tokenProp[0].equals("Comment")) {
//            // TODO: recognize comments, must know when lines end
//            return getNextTokenProperty();
//        }
//        else if (tokenProp[0].equals("MakeUserInstruction")) {
//            // handle making new udc
//            // -- use [ ] as ending conditions
//            
//            // TODO: error check tokens to be expected
//            
//            String name = nodeList.poll()[1]; // name of command
//            nodeList.poll(); // ListStart
//            
//            List<String> vars = new ArrayList<String>();
//            while (!nodeList.peek().equals("ListEnd")){
//                vars.add(nodeList.poll()[1]); // store each variable of the command
//            }
//            
//            nodeList.poll(); // ListEnd
//            nodeList.poll(); // ListStart
//            while (!nodeList.peek().equals("ListEnd")){
//                vars.add(nodeList.poll()[1]); // now get useful tokenProperties
//            }
//            
//    
//            return getNextTokenProperty();
//        }
//        
//        else if (tokenProp[0].equals("MakeVariable")){
//            // handle making new variable
//        	List<String> vars = new ArrayList<String>();
//        	vars.add(nodeList.poll()[1]); //should add until the current expression is ended (what is there now is just for testing)
//        	Database.getInstance().putVariable(tokenProp[1], vars.toArray(new String[vars.size()]));
//           
//            return getNextTokenProperty();
//        }
//        
//        else if (tokenProp[0].equals("Variable")) {
//            // check database if variable/udc exists, replace with value
//
//            if (Database.getInstance().getVariable(tokenProp[1]) == null) {
//                throw new VariableNotFoundException();
//            }
//            else {
//                // -- use deque functionality: addFirst
//            	testForVar();
//            	return Database.getInstance().getVariable(tokenProp[1]);
//                // TODO refactor how we testForVars within brackets
//                
//            }
//        }
//        else if (tokenProp[0].equals("Command")) {
//            // check database if variable/udc exists, replace with value
//            // -- use deque functionality: addFirst
//            // otherwise throw new CommandNotFoundException();
//        	if (Database.getInstance().getVariable(tokenProp[1]) == null) {
//                throw new VariableNotFoundException();
//            }
//            else {
//                // -- use deque functionality: addFirst
//            	testForVar();
//            	return Database.getInstance().getVariable(tokenProp[1]);
//                // TODO refactor how we testForVars within brackets
//            }
//        }
//        else if (tokenProp[0].equals("ListStart")) {
//            // do something... should apply to MakeUserInstructions, iterators, conditionals
//            testForOpenBracket();
//            openBrackets.push("Open");
//            return null;
//        }
//        else if (tokenProp[0].equals("ListEnd")) {
//            // do something... should apply to MakeUserInstructions, iterators, conditionals
//            testForClosedBracket();
//            if (!openBrackets.empty()){
//            	openBrackets.pop();
//            }
//            else {
//            	throw new IncorrectNumberOfBracketsException();
//            }
//            return null;
//        }
//        else {
//            return tokenProp;
//        }
//    }

    private TreeNode getMatch (String token) {
        boolean matched = false;
        if (token.trim().length() > 0) {
            for (Entry<String, Pattern> p : myPatterns) {
                if (match(token, p.getValue())) {
                    // System.out.println(String.format("%s matches %s", token, p.getKey()));
                    matched = true;
                    return NodeFactory.get(new String[] { p.getKey(), token }); // need token for Constant, Variable,
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
