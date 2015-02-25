package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import model.node.NodeFactory;
import model.node.TreeNode;


public class Parser {

    private List<Entry<String, Pattern>> myPatterns;
    private Turtle myTurtle;
    private Deque<String[]> tokenProperties;
    private boolean needRoot = true;
    private TreeNode root = null;
    private TreeNode last = null;

    public Parser (List<Entry<String, Pattern>> patterns, Turtle turtle) {
        myPatterns = patterns;
        myTurtle = turtle;
    }

    public TreeNode parse (String feed) {
        List<String> tokens = Arrays.asList(feed.split("\\p{Z}"));

        // read Resource Bundle, convert tokens to Deque
        tokenProperties = new LinkedList<String[]>(tokens.stream()
                .map(this::getMatch)
                .collect(Collectors.toList()));
        return buildTrees();
    }

    private TreeNode buildTrees () {
        List<TreeNode> treeList = new ArrayList<TreeNode>(); // to be returned

        // build trees for all tokenProperties
        while (!tokenProperties.isEmpty()) {

            // TODO: must handle list starts/ends for iterators/conditionals
            // -- recognize them (error-check) but not put in tree

            // appropriate node generated using tokenProperties
            TreeNode node = getNextNode();

            addChildren(node);

            // node will have all children
            if (needRoot){
            	root = node;
            	last = root;
            	needRoot = false;
            }
            else {
	            last.setNeighbor(node);
	            last = last.getNeighbor();
            }
        }
        return root;
    }
    
    private void addChildren(TreeNode node){
        if (node.allChildrenPresent()){
            return;
        }
        if (tokenProperties.isEmpty()) {
            // throw "unexpected end of instructions" error
            // -- e.g. fd sum 50
        }
        TreeNode childNode = getNextNode();
        node.addChild(childNode);
        
        if (!childNode.allChildrenPresent()) {
            addChildren(childNode); // currentNode
        }       
        return;
    }

    private TreeNode getNextNode () {
        String[] tokenProp = getNextTokenProperty();
        // special case of creating a constant node
        if (tokenProp[0].equals("Constant")) {
            return NodeFactory.getInstance().getConstant(Double.parseDouble(tokenProp[1]));
        }
        else{
            return NodeFactory.getInstance().getNonConstant(tokenProp[0], myTurtle);
        }
    }

    private String[] getNextTokenProperty () {
        String[] tokenProp = tokenProperties.poll();
        
        if (tokenProp[0].equals("Comment")){
            // TODO: recognize comments, must know when lines end
            return getNextTokenProperty(); 
        }
        else if (tokenProp[0].equals("MakeVariable") || tokenProp[0].equals("MakeUserInstruction")) {
            // handle making new variable or udc
            // -- use [ ] as ending conditions
            return getNextTokenProperty();
        }
        else if (tokenProp[0].equals("Variable") || tokenProp[0].equals("Command")) {
            // check database if variable/udc exists, replace with value
            // -- use deque functionality: addFirst
            // otherwise throw command/variable not found exception
            return getNextTokenProperty();
        }
        else if (tokenProp[0].equals("ListStart")) {
            // do something... should apply to MakeUserInstructions, iterators, conditionals
            return null;
        }
        else if (tokenProp[0].equals("ListEnd")) {
            // do something... should apply to MakeUserInstructions, iterators, conditionals
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
                // throw unrecognized token exception
                System.out.println(String.format("%s not matched", token));
            }
        }
        // should never get to this point...
        return null;
    }

    private boolean match (String input, Pattern regex) {
        return regex.matcher(input).matches();
    }
}
