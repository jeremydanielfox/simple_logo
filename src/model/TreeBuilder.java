package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import model.Parser.TokenProperty;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.NodeFactory;
import model.node.Parameters;
import model.node.TreeNode;
import model.node.database.Variable;
import model.node.syntax.Syntax;
import model.node.writer.MakeUserInstruction;
import Exceptions.IncorrectSyntaxException;
import Exceptions.UnclosedListException;
import Exceptions.UnexpectedEndOfInstructionsException;


/**
 * Builds a tree of nodes.
 * @author Nathan Prabhu
 *
 */

public class TreeBuilder {

    private static Workspace myWorkspace;

    
    /**
     * The main method for building the tree
     * @param workspace current workspace
     * @param tokenList TokenProperties produced by the parser
     * @return
     */
    public static CommandList build (Workspace workspace, List<TokenProperty> tokenList) {
        myWorkspace = workspace;
        return buildCommandList(tokenList);
    }

    /**
     * Builds a CommandList. 
     * @param tokenList
     * @return CommandList node
     */
    private static CommandList buildCommandList (List<TokenProperty> tokenList) {
        Queue<TokenProperty> tokenQueue = new LinkedList<TokenProperty>(tokenList);
        List<EvalNode> trees = new ArrayList<EvalNode>();
        CommandList root = new CommandList(trees);
        
        // special case of checking first token
        if (isNextToken(tokenQueue.peek(), "[") || isNextToken(tokenQueue.peek(), "]")) { 
            throw new IncorrectSyntaxException();
        }

        // build trees until nodeList is empty
        while (!tokenQueue.isEmpty()) {
            TreeNode node = getNextNode(tokenQueue.poll());
            addChildren(tokenQueue, node);
            // special case: future nodes might need to know about new commands
            if (node instanceof MakeUserInstruction) {
                ((MakeUserInstruction) node).evaluate();
                continue;
            }
            trees.add((EvalNode) node);
        }
        return root;
    }

    /**
     * Adds children to the given node.
     * @param tokenQueue current token properties
     * @param node node to which children will be added
     */
    private static void addChildren (Queue<TokenProperty> tokenQueue, TreeNode node) {
        if (node instanceof Syntax) { // syntax nodes don't need children
            return;
        }
        EvalNode evalNode = (EvalNode) node;
        
        if (evalNode.allChildrenPresent()) { return; }
        
        // e.g. fd sum 25
        if (tokenQueue.isEmpty()) { throw new UnexpectedEndOfInstructionsException();}

        TreeNode childNode = getNextChild(tokenQueue, evalNode);
        evalNode.addChild(childNode);
        if (!evalNode.allChildrenPresent()) {
            addChildren(tokenQueue, evalNode);
        }
        return;
    }

    /**
     * Retrieves the next Child node to be added to the current node. Checks for special cases and error checks.
     * @param tokenQueue current token properties
     * @param current node to which children will be added
     * @return
     */
    private static TreeNode getNextChild (Queue<TokenProperty> tokenQueue, EvalNode current) {
        // special case 1:
        if (current.getNextType().equals(CommandList.class)) { return extractCommandList(tokenQueue); }
        // special case 2:
        if (current.getNextType().equals(Parameters.class)) {
            if (current instanceof MakeUserInstruction) {
                return extractParameters(tokenQueue, Variable.class);
            }
            else { // all other cases
                return extractParameters(tokenQueue, EvalNode.class);
            }
        }

        // default case:
        TreeNode child = getNextNode(tokenQueue.poll());
        // checks it's the correct type of node desired
        if (current.getNextType().isAssignableFrom(child.getClass())) {
            addChildren(tokenQueue, child);
            return child;
        }
        else {
            throw new IncorrectSyntaxException(); // e.g. "fd [" or "for [ :var 1 ]"
        }
    }

    private static CommandList extractCommandList (Queue<TokenProperty> tokenQueue) {
        List<TokenProperty> snippet = new ArrayList<TokenProperty>();
        Stack<Integer> bracketChecker = new Stack<Integer>();  // integers just used as counters
        recursiveExtract(tokenQueue, snippet, bracketChecker);
        return TreeBuilder.buildCommandList(snippet);
    }

    // gets all commands within a set of brackets
    private static void recursiveExtract (Queue<TokenProperty> tokenQueue,
                                          List<TokenProperty> snippet,
                                          Stack<Integer> bracketChecker) {
        // ran out of nodes
        if (tokenQueue.isEmpty()) { throw new UnclosedListException(); }

        // normal case: end of list and all brackets closed
        if (isNextToken(tokenQueue.peek(), "]") && bracketChecker.isEmpty()) { return; }
        // case: ListEnd seen but other ListStarts to be matched
        if (isNextToken(tokenQueue.peek(), "]")) {
            bracketChecker.pop();
        }
        // case: ListStart seen
        if (isNextToken(tokenQueue.peek(), "[")) {
            bracketChecker.push(new Integer(0)); // integer is just used as counter
        }
        // add to existing snippet
        snippet.add(tokenQueue.poll());
        recursiveExtract(tokenQueue, snippet, bracketChecker);
    }

    // generalized, extract Params or Ids
    private static Parameters extractParameters (Queue<TokenProperty> tokenQueue,
                                                 Class<? extends TreeNode> type) {
        List params = new ArrayList();
        if (tokenQueue.isEmpty()) {
            // throw unclosed List exception
        }
        while (!isNextToken(tokenQueue.peek(), "]")) {
            TreeNode node = getNextNode(tokenQueue.poll());
            if (!(type.isAssignableFrom(node.getClass()))) { throw new IncorrectSyntaxException(); }
            params.add(node);
        }
        return new Parameters(params);
    }

    /**
     * Gets the next TreeNode from the NodeFactory, using the TokenProperty as a key
     * @param tokenProperty
     * @return TreeNode to be put in the tree
     */
    private static TreeNode getNextNode (TokenProperty tokenProperty) {
        return NodeFactory.get(tokenProperty, myWorkspace);
    }

    private static boolean isNextToken (TokenProperty tokenProperty, String token) {
        return tokenProperty.getToken().equals(token);
    }
}
