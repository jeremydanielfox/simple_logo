package model;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import model.Parser.TokenProperty;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.NodeFactory;
import model.node.Parameters;
import model.node.TreeNode;
import model.node.basic.Variable;
import model.node.controlStructure.MakeUserInstruction;
import model.turtle.SingleTurtle;
import Exceptions.IncorrectSyntaxException;
import Exceptions.UnexpectedEndOfInstructionsException;


public class TreeBuilder {
    
    private static SingleTurtle myTurtle;

    public static CommandList build (SingleTurtle turtle, List<TokenProperty> tokenList) {
        myTurtle = turtle;
        return buildCommandList(tokenList);
    }
        
    private static CommandList buildCommandList(List<TokenProperty> tokenList){
        Queue<TokenProperty> tokenQueue = new LinkedList<TokenProperty>(tokenList);
        List<EvalNode> trees = new ArrayList<EvalNode>();
        CommandList root = new CommandList(trees);
        if (isNextToken(tokenQueue, "[") || isNextToken(tokenQueue, "]")){
            throw new IncorrectSyntaxException(); // special case of checking first element
        }
        
        // build trees until nodeList is empty
        while (!tokenQueue.isEmpty()) {
            TreeNode node = getNextNode(tokenQueue);
            addChildren(tokenQueue, node);
            // special case: future nodes might need to know about new commands
            if (node instanceof MakeUserInstruction){
                ((MakeUserInstruction) node).evaluate();
                continue;
            }
            trees.add((EvalNode) node);
        }
        return root;
    }

    private static void addChildren (Queue<TokenProperty> tokenQueue, TreeNode node) {
        if (!(node instanceof EvalNode)) { // syntax nodes don't need children
            return;
        }
        EvalNode evalNode = (EvalNode) node;
        if (evalNode.allChildrenPresent()){
            return;       
        }
        if (tokenQueue.isEmpty()) { 
            throw new UnexpectedEndOfInstructionsException();
            // e.g. fd sum 25
        }
        TreeNode childNode = getNextChild(tokenQueue, evalNode);
        evalNode.addChild(childNode);
        if (!evalNode.allChildrenPresent()) {
            addChildren(tokenQueue, evalNode);
        }
        return;
    }

    private static TreeNode getNextChild (Queue<TokenProperty> tokenQueue, EvalNode current) {
        // special case 1:
        if (current.getNextType().equals(CommandList.class)) {
            return extractCommandList(tokenQueue);
        }
        // special case 2:
        if (current.getNextType().equals(Parameters.class)) {
            return extractParameters(tokenQueue);
        }
        
        // default case: 
        TreeNode child = getNextNode(tokenQueue);
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
        if (tokenQueue.isEmpty()) {
            // throw unclosed List exception
        }

        // normal case: end of list and all brackets closed
        if (isNextToken(tokenQueue, "]") && bracketChecker.isEmpty()) { return; }
        // case: ListEnd seen but other ListStarts to be matched
        if (isNextToken(tokenQueue, "]")) {
            bracketChecker.pop();
        }
        // case: ListStart seen
        if (isNextToken(tokenQueue, "[")) {
            bracketChecker.push(new Integer(0)); // integer is just used as counter
        }
        // add to existing snippet
        snippet.add(tokenQueue.poll());
        recursiveExtract(tokenQueue, snippet, bracketChecker);
    }
    
    private static Parameters extractParameters (Queue<TokenProperty> tokenQueue){
        List<Variable> params = new ArrayList<Variable>();
        if (tokenQueue.isEmpty()) {
            // throw unclosed List exception
        }
        while (!isNextToken(tokenQueue, "]")) {
            TreeNode node = getNextNode(tokenQueue);
            if (!(node instanceof Variable)){
                // throw Expected Variable exception
            }
            params.add((Variable) node);
        }
        return new Parameters(params);
    }
    
    private static TreeNode getNextNode(Queue<TokenProperty> tokenQueue){
        return NodeFactory.get(tokenQueue.poll(), myTurtle);
    }
    
    private static boolean isNextToken(Queue<TokenProperty> tokenQueue, String token){
        return tokenQueue.peek().getToken().equals(token);
    }
}
