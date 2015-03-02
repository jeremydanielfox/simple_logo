package model;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import model.node.CommandList;
import model.node.EvalNode;
import model.node.Parameters;
import model.node.TreeNode;
import model.node.basic.Variable;
import model.node.controlStructure.MakeUserInstruction;
import model.node.syntax.ListEnd;
import model.node.syntax.ListStart;
import Exceptions.IncorrectSyntaxException;
import Exceptions.UnexpectedEndOfInstructionsException;


public class TreeBuilder {

    public static CommandList build (Queue<TreeNode> nodeList) {
        List<EvalNode> trees = new ArrayList<EvalNode>();
        CommandList root = new CommandList(trees);
        if (!(nodeList.peek() instanceof EvalNode)){
            throw new IncorrectSyntaxException(); // special case of checking first element
        }
        
        // build trees until nodeList is empty
        while (!nodeList.isEmpty()) {
            TreeNode node = nodeList.poll();
            addChildren(node, nodeList);
            // special case: future nodes might need to know about new commands
            if (node instanceof MakeUserInstruction){
                ((MakeUserInstruction) node).evaluate();
                continue;
            }
            trees.add((EvalNode) node);
        }
        return root;
    }

    private static void addChildren (TreeNode node, Queue<TreeNode> nodeList) {
        if (!(node instanceof EvalNode)) {
            return;
        }
        EvalNode evalNode = (EvalNode) node;
        if (evalNode.allChildrenPresent()){
            return;       
        }
        if (nodeList.isEmpty()) { 
            throw new UnexpectedEndOfInstructionsException();
            // e.g. fd sum 25
        }
        TreeNode childNode = getNextChild(evalNode, nodeList);
        evalNode.addChild(childNode);
        if (!evalNode.allChildrenPresent()) {
            addChildren(evalNode, nodeList);
        }
        return;
    }

    private static TreeNode getNextChild (EvalNode current, Queue<TreeNode> nodeList) {
        // special case 1:
        if (current.getNextType().equals(CommandList.class)) {
            return extractCommandList(nodeList);
        }
        // special case 2:
        if (current.getNextType().equals(Parameters.class)) {
            return extractParameters(nodeList);
        }
        
        // default case: 
        TreeNode child = nodeList.poll();
        // checks it's the correct type of node desired
        if (current.getNextType().isAssignableFrom(child.getClass())) {
            addChildren(child, nodeList);
            return child;
        }
        else {
            throw new IncorrectSyntaxException(); // e.g. "fd [" or "for [ :var 1 ]" 
        }
    }

    private static CommandList extractCommandList (Queue<TreeNode> nodeList) {
        Queue<TreeNode> snippet = new LinkedList<TreeNode>();
        Stack<TreeNode> bracketChecker = new Stack<TreeNode>();
        recursiveExtract(nodeList, snippet, bracketChecker);
        return build(snippet);
    }

    // gets all commands within a set of brackets
    private static void recursiveExtract (Queue<TreeNode> nodeList,
                                          Queue<TreeNode> snippet,
                                          Stack<TreeNode> bracketChecker) {
        // ran out of nodes
        if (nodeList.isEmpty()) {
            // throw unclosed List exception
        }

        // normal case: end of list and all brackets closed
        if (nodeList.peek() instanceof ListEnd && bracketChecker.isEmpty()) { return; }
        // case: ListEnd seen but other ListStarts to be matched
        if (nodeList.peek() instanceof ListEnd) {
            bracketChecker.pop();
        }
        // case: ListStart seen
        if (nodeList.peek() instanceof ListStart) {
            bracketChecker.push(nodeList.peek());
        }
        // add to existing snippet
        snippet.add(nodeList.poll());
        recursiveExtract(nodeList, snippet, bracketChecker);
    }
    
    private static Parameters extractParameters (Queue<TreeNode> nodeList){
        List<Variable> params = new ArrayList<Variable>();
        if (nodeList.isEmpty()) {
            // throw unclosed List exception
        }
        while (!(nodeList.peek() instanceof ListEnd)) {
            TreeNode node = nodeList.poll();
            if (!(node instanceof Variable)){
                // throw Expected Variable exception
            }
            params.add((Variable) node);
        }
        return new Parameters(params);
    }

}
