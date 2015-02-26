package model.node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class ChildBuilder {

    public ChildBuilder(Deque<String> childNames, Queue<Class<? extends TreeNode>> expectedNodes){
        // assign properly
    }
    
    public String verifyChild(TreeNode node){
        // ensure appropriate number of children (from polling the queues)
        
        // get name from childNames
        // ensure node is correct type
        
        // TODO: report when too little children added
        // also when too many added
        
    }
    
    // another method that checks if queues are empty
}
