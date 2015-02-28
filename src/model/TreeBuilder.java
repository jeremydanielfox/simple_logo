package model;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import model.node.ChildBuilder;
//import model.node.CommandList;
import model.node.TreeNode;
import Exceptions.IncorrectNumberOfBracketsException;
import Exceptions.IncorrectSyntaxException;
import Exceptions.UnexpectedEndOfInstructionsException;

public class TreeBuilder {
    
    public static TreeNode build (Queue<TreeNode> nodeList) {
        // build trees for all tokenProperties
        TreeNode root = null;
        TreeNode last = null;
        boolean needRoot = true;
        while (!nodeList.isEmpty()) {

            // appropriate node generated using tokenProperties
            TreeNode node = nodeList.poll();
            addChildren(node, nodeList);

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

    private static void addChildren (TreeNode node, Queue<TreeNode> nodeList) {
        if (node.allChildrenPresent()) { 
            return; 
            }
        if (nodeList.isEmpty()) {
            throw new UnexpectedEndOfInstructionsException();
        // -- e.g. fd sum 50
        }

        TreeNode childNode = getNextChild(node, nodeList);
        //TreeNode childNode = nodeList.poll();
        //addChildren(childNode, nodeList);
        node.addChild(childNode);
        if (!node.allChildrenPresent()){
            addChildren(node, nodeList);
        }
        return;
    }
    
    private static TreeNode getNextChild (TreeNode current, Queue<TreeNode> nodeList) {
        if (current.getNextType().equals(CommandList.class)){
            // remove the CommandList.class type
            current.removeChildBuilder();
            
            // get all nodes until proper closed bracket
            Queue<TreeNode> snippet = extract(nodeList);
            // build Tree with snippet
            TreeNode node = build(snippet); // should return a CommandList node
            return node; //should be directly added to the node, no need to add children to it  
        }
        TreeNode child = nodeList.poll();
        // checks it's the correct type of node desired
        if (current.getNextType().isAssignableFrom(child.getClass())){
            addChildren(child, nodeList);
            return child;
        }
        else{
            throw new IncorrectSyntaxException();
        }
    }
    
    private static Queue<TreeNode> extract(Queue<TreeNode> nodeList){
        Queue<TreeNode> snippet = new LinkedList<TreeNode>();
        Stack<TreeNode> bracketChecker = new Stack<TreeNode>();
        recursiveExtract(nodeList, snippet, bracketChecker);
        return snippet;
    }
    
    private static void recursiveExtract(Queue<TreeNode> nodeList, Queue<TreeNode> snippet, Stack<TreeNode> bracketChecker){
        // ran out of nodes
        if (nodeList.isEmpty()){
            // throw unclosed List exception
        }
        
        // normal case: end of list and all brackets closed
        if (nodeList.peek() instanceof ListEnd && bracketChecker.isEmpty()){
               return;
        }
        // case: ListEnd seen but other ListStarts to be matched
        if (nodeList.peek() instanceof ListEnd){
            bracketChecker.pop();
        }
        // case: ListStart seen
        if (nodeList.peek() instanceof ListStart){
            bracketChecker.push(nodeList.peek());
        }
        // add to existing snippet
        snippet.add(nodeList.poll());
        recursiveExtract(nodeList, snippet, bracketChecker);
    }
    
}
